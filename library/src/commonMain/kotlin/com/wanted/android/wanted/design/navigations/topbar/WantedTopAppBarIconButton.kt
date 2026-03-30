package com.wanted.android.wanted.design.navigations.topbar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarContract.Variant
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
fun WantedTopAppBarIconButton(
    painter: Painter,
    modifier: Modifier = Modifier,
    variant: Variant = LocalWantedTopBarIconVariant.current,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    tint: Color = LocalWantedTopBarIconTint.current,
    badgeAlignment: Alignment = Alignment.TopEnd,
    badge: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    WantedTouchArea(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        verticalPadding = 8.dp,
        horizontalPadding = 8.dp,
        interactionSource = interactionSource,
        shape = CircleShape,
        content = {
            Box(modifier = Modifier) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painter,
                    contentDescription = null,
                    tint = tint
                )

                badge?.let {
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = badgeAlignment
                    ) {
                        badge()
                    }
                }
            }
        }
    )
}

val LocalWantedTopBarIconVariant = WantedTopBarIconVariantCompositionLocal()

class WantedTopBarIconVariantCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<Variant> = staticCompositionLocalOf { Variant.Normal }
) {
    val current: Variant
        @Composable get() = delegate.current

    infix fun provides(value: Variant) = delegate provides value
}

val LocalWantedTopBarIconTint = WantedTopBarIconTintCompositionLocal()

interface WantedTopBarIconTintLoader {
    @Composable
    fun getColor(): Color
}

private open class WantedTopBarIconTintImpl : WantedTopBarIconTintLoader {
    @Composable
    override fun getColor(): Color = DesignSystemTheme.colors.labelNormal
}

class WantedTopBarIconTintCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<WantedTopBarIconTintLoader> = staticCompositionLocalOf { WantedTopBarIconTintImpl() }
) {
    val current: Color
        @Composable get() = delegate.current.getColor()

    infix fun provides(value: Color) = delegate provides object : WantedTopBarIconTintImpl() {
        @Composable
        override fun getColor(): Color = value
    }
}
