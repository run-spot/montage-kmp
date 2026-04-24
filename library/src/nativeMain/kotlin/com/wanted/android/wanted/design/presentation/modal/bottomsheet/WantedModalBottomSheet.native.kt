package com.wanted.android.wanted.design.presentation.modal.bottomsheet

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalSize
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.presentation.modal.view.WantedDialogLayout
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import kotlin.math.min as minFloat
import kotlin.math.roundToInt

@Composable
actual fun WantedModalBottomSheet(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    background: Color,
    type: ModalType,
    modalSize: ModalSize,
    dismissOnClickOutside: Boolean,
    topBar: @Composable (() -> Unit)?,
    bottomBar: (@Composable () -> Unit)?,
    content: @Composable () -> Unit
) {
    if (!isShow) {
        return
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside,
            usePlatformDefaultWidth = false
        )
    ) {
        val outsideInteractionSource = remember { MutableInteractionSource() }
        val sheetInteractionSource = remember { MutableInteractionSource() }
        val density = LocalDensity.current
        var sheetOffsetY by remember(isShow) { mutableFloatStateOf(0f) }
        var sheetHeight by remember { mutableFloatStateOf(0f) }
        val dismissThreshold = with(density) { 96.dp.toPx() }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = outsideInteractionSource,
                    indication = null,
                    enabled = dismissOnClickOutside,
                    onClick = onDismissRequest
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
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
                                            val threshold = minFloat(
                                                sheetHeight * 0.25f,
                                                dismissThreshold
                                            )

                                            if (sheetOffsetY > threshold) {
                                                onDismissRequest()
                                            } else {
                                                sheetOffsetY = 0f
                                            }
                                        },
                                        onDragCancel = {
                                            sheetOffsetY = 0f
                                        },
                                        onDrag = { change, dragAmount ->
                                            val deltaY = dragAmount.y
                                            if (deltaY != 0f) {
                                                sheetOffsetY = (sheetOffsetY + deltaY)
                                                    .coerceAtLeast(0f)
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
