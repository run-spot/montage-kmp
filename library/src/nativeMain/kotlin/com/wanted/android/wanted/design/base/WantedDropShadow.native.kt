package com.wanted.android.wanted.design.base

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowSpreadStyle
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowStyle
import org.jetbrains.skia.RRect

@Composable
actual fun Modifier.wantedDropShadow(style: WantedShadowStyle): Modifier {
    val cachedShadows = remember(style) { style.getShadow() }

    return this
        .dropShadow(
            shadows = cachedShadows,
            borderRadius = style.borderRadius,
        )
        .background(
            color = style.backgroundColor,
            shape = RoundedCornerShape(style.borderRadius)
        )
}

@Composable
actual fun Modifier.wantedDropShadowSpread(style: WantedShadowSpreadStyle): Modifier {
    val cachedShadows = remember(style) { style.getShadow() }

    return this
        .dropShadow(
            shadows = cachedShadows,
            borderRadius = style.borderRadius,
        )
        .background(
            color = style.backgroundColor,
            shape = RoundedCornerShape(style.borderRadius)
        )
}

private fun Modifier.dropShadow(
    shadows: List<WantedDropShadowDefaults.WantedShadowToken>,
    borderRadius: Dp
) = this.then(
    Modifier.drawBehind {
        if (shadows.isEmpty()) return@drawBehind

        drawIntoCanvas { canvas ->
            val skiaCanvas = canvas.nativeCanvas
            val borderRadiusPx = borderRadius.toPx()

            for (i in shadows.size - 1 downTo 0) {
                val shadow = shadows[i]
                val spreadPx = shadow.spreadRadius.toPx()
                val left = -spreadPx
                val top = -spreadPx
                val right = size.width + spreadPx
                val bottom = size.height + spreadPx
                val rRect = RRect.makeLTRB(
                    l = left,
                    t = top,
                    r = right,
                    b = bottom,
                    radius = (borderRadiusPx + spreadPx).coerceAtLeast(0f)
                )

                skiaCanvas.drawRectShadow(
                    r = rRect,
                    dx = shadow.offsetX.toPx(),
                    dy = shadow.offsetY.toPx(),
                    blur = shadow.blurRadius.toPx(),
                    spread = 0f,
                    color = shadow.color.toArgb()
                )
            }
        }
    }
)
