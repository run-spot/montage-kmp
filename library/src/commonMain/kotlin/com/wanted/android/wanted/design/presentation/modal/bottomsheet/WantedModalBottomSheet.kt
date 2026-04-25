package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarDefaults
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalSize
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.presentation.modal.view.WantedDialogLayout
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import kotlinx.coroutines.launch
import kotlin.math.min as minFloat
import kotlin.math.roundToInt

/**
 * Bottom sheet modal component.
 */
@Composable
fun WantedModalBottomSheet(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
    type: ModalType = ModalType.Flexible,
    modalSize: ModalSize = ModalSize.Medium,
    dismissOnClickOutside: Boolean = true,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val currentDismissRequest by rememberUpdatedState(onDismissRequest)
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    var isDialogVisible by remember { mutableStateOf(false) }
    var hasPlayedOpenAnimation by remember { mutableStateOf(false) }
    var sheetOffsetY by remember { mutableFloatStateOf(0f) }
    var sheetHeight by remember { mutableFloatStateOf(0f) }
    val dismissThreshold = with(density) { 96.dp.toPx() }
    val canDismiss = type.isCloseable

    suspend fun animateSheetTo(targetValue: Float) {
        animate(
            initialValue = sheetOffsetY,
            targetValue = targetValue,
            animationSpec = tween()
        ) { value, _ ->
            sheetOffsetY = value
        }
    }

    fun animateDismiss(notify: Boolean) {
        coroutineScope.launch {
            if (sheetHeight > 0f) {
                animateSheetTo(sheetHeight)
            }
            isDialogVisible = false
            if (notify) {
                currentDismissRequest()
            }
        }
    }

    LaunchedEffect(isShow) {
        if (isShow) {
            isDialogVisible = true
            hasPlayedOpenAnimation = false
        } else if (isDialogVisible) {
            if (sheetHeight > 0f) {
                animateSheetTo(sheetHeight)
            }
            isDialogVisible = false
        }
    }

    LaunchedEffect(isDialogVisible, isShow, sheetHeight) {
        if (isDialogVisible && isShow && sheetHeight > 0f && !hasPlayedOpenAnimation) {
            hasPlayedOpenAnimation = true
            sheetOffsetY = sheetHeight
            animateSheetTo(0f)
        }
    }

    if (!isDialogVisible) {
        return
    }

    Dialog(
        onDismissRequest = {
            if (canDismiss) {
                animateDismiss(notify = true)
            }
        },
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside && canDismiss,
            usePlatformDefaultWidth = false
        )
    ) {
        val outsideInteractionSource = remember { MutableInteractionSource() }
        val sheetInteractionSource = remember { MutableInteractionSource() }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WantedTopAppBarDefaults.windowInsets)
                .clickable(
                    interactionSource = outsideInteractionSource,
                    indication = null,
                    enabled = dismissOnClickOutside && canDismiss,
                    onClick = {
                        animateDismiss(notify = true)
                    }
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .imePadding()
                    .then(wantedBottomSheetHeightModifier(type, maxHeight))
                    .offset {
                        IntOffset(
                            x = 0,
                            y = sheetOffsetY.roundToInt()
                        )
                    }
                    .onGloballyPositioned {
                        sheetHeight = it.size.height.toFloat()
                    }
                    .clickable(
                        interactionSource = sheetInteractionSource,
                        indication = null,
                        onClick = {}
                    ),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = background,
                contentColor = background,
                tonalElevation = 0.dp
            ) {
                Column(Modifier.fillMaxWidth()) {
                    if (type.shouldShowDragHandle()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .pointerInput(type, sheetHeight) {
                                    detectDragGestures(
                                        onDragEnd = {
                                            if (shouldDismissBottomSheet(
                                                    sheetOffsetY = sheetOffsetY,
                                                    sheetHeight = sheetHeight,
                                                    dismissThreshold = dismissThreshold
                                                )
                                            ) {
                                                animateDismiss(notify = true)
                                            } else {
                                                coroutineScope.launch {
                                                    animateSheetTo(0f)
                                                }
                                            }
                                        },
                                        onDragCancel = {
                                            coroutineScope.launch {
                                                animateSheetTo(0f)
                                            }
                                        },
                                        onDrag = { change, dragAmount ->
                                            val deltaY = dragAmount.y
                                            if (deltaY != 0f) {
                                                sheetOffsetY = (sheetOffsetY + deltaY).coerceAtLeast(0f)
                                                change.consume()
                                            }
                                        }
                                    )
                                },
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            WantedBottomSheetDefaults.DragHandle()
                        }
                    }

                    WantedDialogLayout(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = background,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        modalSize = modalSize,
                        topBar = topBar,
                        content = content,
                        bottomBar = bottomBar
                    )
                }
            }
        }
    }
}

internal fun shouldDismissBottomSheet(
    sheetOffsetY: Float,
    sheetHeight: Float,
    dismissThreshold: Float
): Boolean {
    val threshold = minFloat(
        sheetHeight * 0.25f,
        dismissThreshold
    )
    return sheetOffsetY > threshold
}

private fun wantedBottomSheetHeightModifier(
    type: ModalType,
    screenHeight: Dp,
    maxHeight: Dp? = null
): Modifier {
    return when (type) {
        is ModalType.Fixed -> {
            Modifier.height(min(type.height, maxHeight ?: type.height))
        }

        is ModalType.FixedFullScreen -> {
            Modifier.height(min(screenHeight, maxHeight ?: screenHeight))
        }

        is ModalType.FixedRatio -> {
            val ratioHeight = (screenHeight.value * type.ratio).dp
            Modifier.height(min(ratioHeight, maxHeight ?: ratioHeight))
        }

        else -> {
            Modifier.heightIn(max = min(screenHeight, maxHeight ?: screenHeight))
        }
    }
}
