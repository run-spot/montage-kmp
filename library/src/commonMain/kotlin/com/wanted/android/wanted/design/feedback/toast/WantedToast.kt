package com.wanted.android.wanted.design.feedback.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.wanted.android.wanted.design.actions.button.WantedButton
import com.wanted.android.wanted.design.feedback.WantedToastIcon
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_eye_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedToast(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(0),
) {
    SnackbarHost(
        modifier = modifier.zIndex(1000f),
        hostState = snackbarHostState
    ) {
        val visuals = it.visuals
        if (visuals is WantedToastVisuals) {
            WantedToastImpl(
                variant = visuals.variant,
                windowInsets = windowInsets,
                text = visuals.message,
                icon = visuals.icon
            )
        } else {
            WantedToastImpl(
                windowInsets = windowInsets,
                text = visuals.message,
            )
        }
    }
}

@Composable
internal fun WantedToastImpl(
    text: String,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(0),
    variant: WantedToastVariant = WantedToastVariant.Message,
    icon: @Composable (() -> Unit)? = null
) {
    val backgroundTintColor = DesignSystemTheme.colors.staticWhite
    val tintColor = when (variant) {
        WantedToastVariant.Message -> null
        WantedToastVariant.Positive -> DesignSystemTheme.colors.statusPositive
        WantedToastVariant.Cautionary -> DesignSystemTheme.colors.statusCautionary
        WantedToastVariant.Negative -> DesignSystemTheme.colors.statusNegative
    }

    val iconSlot: @Composable (() -> Unit)? = when (variant) {
        WantedToastVariant.Message -> icon
        else -> {
            {
                WantedToastIcon(
                    modifier = Modifier.fillMaxSize(),
                    resource = requireNotNull(variant.resource),
                    backgroundResource = variant.backgroundResource,
                    backgroundColor = backgroundTintColor,
                    tint = requireNotNull(tintColor)
                )
            }
        }
    }

    WantedToastLayout(
        modifier = modifier,
        icon = iconSlot,
        windowInsets = windowInsets,
        content = {
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    )
}

@Composable
private fun WantedToastLayout(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(0),
    icon: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(20.dp)
            .windowInsetsPadding(windowInsets)
            .wrapContentHeight()
            .widthIn(max = 420.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(DesignSystemTheme.colors.backgroundNormalNormal)
            .background(DesignSystemTheme.colors.inverseBackground.copy(0.52f))
            .background(DesignSystemTheme.colors.primaryNormal.copy(0.05f))
            .padding(horizontal = 16.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        icon?.let {
            Box(modifier = Modifier.size(22.dp)) {
                icon()
            }
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 5.dp)
                .weight(1f)
                .wrapContentHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            ProvideTextStyle(
                value = DesignSystemTheme.typography.body2Bold.copy(
                    color = DesignSystemTheme.colors.staticWhite
                )
            ) {
                content()
            }
        }
    }
}

fun SnackbarHostState.showToast(
    scope: CoroutineScope,
    message: String,
    variant: WantedToastVariant = WantedToastVariant.Message,
) {
    scope.launch {
        currentSnackbarData?.dismiss()
        showSnackbar(
            visuals = WantedToastVisuals(
                message = message,
                variant = variant
            )
        )
    }
}

@Composable
private fun ToastPreviewContent() {
    val hostState: SnackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            WantedToast(hostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WantedButton(text = "toast") {
                coroutineScope.launch {
                    hostState.showSnackbar(message = "메시지에 마침표를 찍어요.")
                }
            }

            WantedButton(text = "toast Visuals") {
                coroutineScope.launch {
                    hostState.showSnackbar(
                        visuals = WantedToastVisuals(
                            variant = WantedToastVariant.Positive,
                            message = "메시지에 마침표를 찍어요."
                        )
                    )
                }
            }

            WantedToastImpl(variant = WantedToastVariant.Message, text = "메시지에 마침표를 찍어요.")
            WantedToastImpl(variant = WantedToastVariant.Positive, text = "메시지에 마침표를 찍어요.")
            WantedToastImpl(variant = WantedToastVariant.Cautionary, text = "메시지에 마침표를 찍어요.")
            WantedToastImpl(variant = WantedToastVariant.Negative, text = "메시지에 마침표를 찍어요.")
            WantedToastImpl(
                icon = {
                    Icon(
                        contentDescription = "icon",
                        painter = painterResource(Res.drawable.icon_normal_eye_fill),
                        modifier = Modifier.size(22.dp),
                        tint = DesignSystemTheme.colors.statusNegative
                    )
                },
                text = "메시지에 마침표를 찍어요."
            )
        }
    }
}
