package com.wanted.android.wanted.design.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
fun WantedDropShadow(
    modifier: Modifier = Modifier,
    background: Color = DesignSystemTheme.colors.transparent,
    dropShadowColor: Color = DesignSystemTheme.colors.staticBlack.copy(0.03f),
    blur: Dp = 1.dp,
    shape: Shape
) {
    androidx.compose.foundation.layout.Box(modifier = modifier) {
        androidx.compose.foundation.layout.Box(
            Modifier
                .matchParentSize()
                .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .border(
                    shape = shape,
                    color = dropShadowColor,
                    width = 1.dp
                )
        )

        androidx.compose.foundation.layout.Box(
            Modifier
                .matchParentSize()
                .background(
                    shape = shape,
                    color = background
                )
        )
    }
}

@Composable
expect fun Modifier.wantedDropShadow(
    style: WantedDropShadowDefaults.WantedShadowStyle
): Modifier

@Composable
expect fun Modifier.wantedDropShadowSpread(
    style: WantedDropShadowDefaults.WantedShadowSpreadStyle
): Modifier

object WantedDropShadowDefaults {
    sealed class WantedShadowStyle(
        open val borderRadius: Dp,
        open val backgroundColor: Color
    ) {
        abstract fun getShadow(): List<WantedShadowToken>

        data class XSmall(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 1.dp, 0.87.dp, (-1).dp, Color(0x1A171717))
                )
            }

            override fun getShadow() = shadowList
        }

        data class Small(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 2.dp, 1.73.dp, (-2).dp, Color(0x0F171717)),
                    WantedShadowToken(0.dp, 4.dp, 4.33.dp, (-1).dp, Color(0x0F171717))
                )
            }

            override fun getShadow() = shadowList
        }

        data class Medium(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 4.dp, 3.46.dp, (-2).dp, Color(0x12000000)),
                    WantedShadowToken(0.dp, 10.dp, 10.39.dp, (-3).dp, Color(0x12171717))
                )
            }

            override fun getShadow() = shadowList
        }

        data class Large(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 6.dp, 5.20.dp, (-4).dp, Color(0x14171717)),
                    WantedShadowToken(0.dp, 16.dp, 15.59.dp, (-6).dp, Color(0x14171717))
                )
            }

            override fun getShadow() = shadowList
        }

        data class XLarge(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 10.dp, 8.66.dp, (-5).dp, Color(0x1A171717)),
                    WantedShadowToken(0.dp, 24.dp, 24.25.dp, (-10).dp, Color(0x1F171717))
                )
            }

            override fun getShadow() = shadowList
        }
    }

    sealed class WantedShadowSpreadStyle(
        open val borderRadius: Dp,
        open val backgroundColor: Color
    ) {
        abstract fun getShadow(): List<WantedShadowToken>

        data class Small(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowSpreadStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 0.dp, 60.dp, 0.dp, Color(0x1A171717))
                )
            }

            override fun getShadow() = shadowList
        }

        data class Medium(
            override val borderRadius: Dp = 12.dp,
            override val backgroundColor: Color = Color.Transparent
        ) : WantedShadowSpreadStyle(borderRadius, backgroundColor) {
            private val shadowList by lazy {
                listOf(
                    WantedShadowToken(0.dp, 15.dp, 75.dp, 0.dp, Color(0x1A171717))
                )
            }

            override fun getShadow() = shadowList
        }
    }

    data class WantedShadowToken(
        val offsetX: Dp,
        val offsetY: Dp,
        val blurRadius: Dp,
        val spreadRadius: Dp,
        val color: Color
    )
}
