package com.wanted.android.wanted.design.presentation.tooltip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_5
import com.wanted.android.wanted.design.util.OPACITY_88

interface WantedTooltipState {
    fun show()
    fun dismiss()
    val isVisible: Boolean
    val visibleState: State<Boolean>
}

private class WantedTooltipStateImpl(
    initialVisible: Boolean
) : WantedTooltipState {
    private val _visibleState = mutableStateOf(initialVisible)
    override val visibleState: State<Boolean>
        get() = _visibleState

    override fun show() {
        _visibleState.value = true
    }

    override fun dismiss() {
        _visibleState.value = false
    }

    override val isVisible: Boolean
        get() = _visibleState.value
}

@Composable
fun rememberTooltipState(initialVisible: Boolean = false): WantedTooltipState =
    remember { WantedTooltipStateImpl(initialVisible) }

internal const val SpacingBetweenTooltipAndAnchor = 8
internal const val SpacingBetweenTooltipAndAnchorNotArrow = 2

enum class WantedTooltipSize {
    Small,
    Medium
}

enum class WantedTooltipAlign {
    Left,
    Center,
    Right
}

internal fun calculateTooltipOffsetX(
    align: WantedTooltipAlign,
    contentPositionX: Float,
    contentWidth: Int,
    tooltipWidth: Int,
    screenWidthPx: Int,
    paddingPx: Int
): Int {
    if (tooltipWidth == 0) return 0

    val idealOffsetX = when (align) {
        WantedTooltipAlign.Left -> 0
        WantedTooltipAlign.Center -> (contentWidth - tooltipWidth) / 2
        WantedTooltipAlign.Right -> contentWidth - tooltipWidth
    }

    val tooltipAbsoluteLeft = contentPositionX + idealOffsetX
    val tooltipAbsoluteRight = tooltipAbsoluteLeft + tooltipWidth

    return when {
        tooltipAbsoluteLeft < paddingPx -> (paddingPx - contentPositionX).toInt()
        tooltipAbsoluteRight > screenWidthPx - paddingPx -> {
            (screenWidthPx - paddingPx - tooltipWidth - contentPositionX).toInt()
        }

        else -> idealOffsetX
    }
}

internal fun calculateCaretPositionX(
    align: WantedTooltipAlign,
    contentWidth: Int,
    tooltipOffsetX: Int,
    caretPaddingHorizontalPx: Float,
    caretWidthPx: Float
): Float {
    val center = contentWidth / 2f
    val contentAnchorX = when (align) {
        WantedTooltipAlign.Left -> {
            val result = caretPaddingHorizontalPx + caretWidthPx
            if (result > center) center else result
        }

        WantedTooltipAlign.Center -> center
        WantedTooltipAlign.Right -> {
            val result = contentWidth - caretPaddingHorizontalPx - caretWidthPx
            if (result < center) center else result
        }
    }

    return contentAnchorX - tooltipOffsetX
}

@Composable
internal fun WantedTooltipLayout(
    modifier: Modifier = Modifier,
    spacingBetweenTooltipAndAnchor: Dp,
    size: WantedTooltipSize,
    text: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .sizeIn(
                minWidth = if (size == WantedTooltipSize.Small) 36.dp else 64.dp,
                maxWidth = 296.dp
            )
            .padding(vertical = spacingBetweenTooltipAndAnchor)
            .background(
                DesignSystemTheme.colors.backgroundNormalNormal,
                RoundedCornerShape(if (size == WantedTooltipSize.Small) 6.dp else 8.dp)
            )
            .background(
                DesignSystemTheme.colors.inverseBackground.copy(OPACITY_88),
                RoundedCornerShape(if (size == WantedTooltipSize.Small) 6.dp else 8.dp)
            )
            .background(
                DesignSystemTheme.colors.primaryNormal.copy(OPACITY_5),
                RoundedCornerShape(if (size == WantedTooltipSize.Small) 6.dp else 8.dp)
            )
            .padding(
                horizontal = if (size == WantedTooltipSize.Small) 8.dp else 10.dp,
                vertical = if (size == WantedTooltipSize.Small) 5.dp else 10.dp
            )
            .padding(horizontal = if (size == WantedTooltipSize.Small) 0.dp else 2.dp)
    ) {
        ProvideTextStyle(
            value = (if (size == WantedTooltipSize.Small) {
                DesignSystemTheme.typography.caption2Medium
            } else {
                DesignSystemTheme.typography.label1Medium
            }).copy(DesignSystemTheme.colors.inverseLabel)
        ) {
            text()
        }
    }
}

@Composable
internal fun WantedTooltipContentsLayout(
    modifier: Modifier = Modifier,
    spacingBetweenTooltipAndAnchor: Dp,
    text: @Composable () -> Unit,
    onClose: @Composable (() -> Unit)?,
    action: @Composable (() -> Unit)?
) {
    Box(
        modifier = modifier
            .sizeIn(minWidth = 64.dp, maxWidth = 296.dp)
            .padding(spacingBetweenTooltipAndAnchor)
            .background(DesignSystemTheme.colors.backgroundNormalNormal, RoundedCornerShape(8.dp))
            .background(DesignSystemTheme.colors.inverseBackground.copy(OPACITY_88), RoundedCornerShape(8.dp))
            .background(DesignSystemTheme.colors.primaryNormal.copy(OPACITY_5), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier.weight(1f, fill = false),
                contentAlignment = Alignment.CenterStart
            ) {
                text()
            }

            onClose?.let {
                Box(modifier = Modifier.size(20.dp)) {
                    onClose()
                }
            }
        }

        action?.let {
            Box(
                modifier = Modifier.padding(start = 2.dp, top = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                action()
            }
        }
    }
}

internal fun Modifier.drawCaret(
    anchorLayoutCoordinates: LayoutCoordinates?,
    onDraw: CacheDrawScope.() -> DrawResult
): Modifier = this.drawWithCache {
    onDraw()
}
