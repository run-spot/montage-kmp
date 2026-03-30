package com.wanted.android.wanted.design.base

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowSpreadStyle
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowStyle

@Composable
actual fun Modifier.wantedDropShadow(style: WantedShadowStyle): Modifier {
    val cachedShadows = remember(style) { style.getShadow() }

    return this
        .dropShadow(
            shadows = cachedShadows,
            borderRadius = style.borderRadius,
            isBackgroundTransparent = style.backgroundColor == Color.Transparent
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
            isBackgroundTransparent = style.backgroundColor == Color.Transparent
        )
        .background(
            color = style.backgroundColor,
            shape = RoundedCornerShape(style.borderRadius)
        )
}

private fun Modifier.dropShadow(
    shadows: List<WantedDropShadowDefaults.WantedShadowToken>,
    borderRadius: Dp = 0.dp,
    isBackgroundTransparent: Boolean = false
) = this.then(
    Modifier.drawBehind {
        if (shadows.isEmpty()) return@drawBehind

        val blurMaskFilterCache = mutableMapOf<Float, BlurMaskFilter>()

        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()

            var clipPath: Path? = null
            val borderRadiusPx = borderRadius.toPx()

            if (isBackgroundTransparent && borderRadiusPx > 0f) {
                clipPath = Path().apply {
                    addRoundRect(
                        androidx.compose.ui.geometry.RoundRect(
                            left = 0f,
                            top = 0f,
                            right = size.width,
                            bottom = size.height,
                            radiusX = borderRadiusPx,
                            radiusY = borderRadiusPx
                        )
                    )
                }
            }

            for (i in shadows.size - 1 downTo 0) {
                val shadow = shadows[i]
                frameworkPaint.color = shadow.color.toArgb()

                val blurRadiusPx = shadow.blurRadius.toPx()
                frameworkPaint.maskFilter = if (blurRadiusPx > 0f) {
                    blurMaskFilterCache.getOrPut(blurRadiusPx) {
                        BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
                    }
                } else {
                    null
                }

                val spreadPixel = shadow.spreadRadius.toPx()
                val offsetXPx = shadow.offsetX.toPx()
                val offsetYPx = shadow.offsetY.toPx()

                val left = -spreadPixel + offsetXPx
                val top = -spreadPixel + offsetYPx
                val right = size.width + spreadPixel + offsetXPx
                val bottom = size.height + spreadPixel + offsetYPx

                var needsRestore = false
                if (isBackgroundTransparent) {
                    canvas.save()
                    needsRestore = true
                    clipPath?.let { path -> canvas.clipPath(path, ClipOp.Difference) }
                }

                if (borderRadiusPx > 0f) {
                    canvas.drawRoundRect(
                        left = left,
                        top = top,
                        right = right,
                        bottom = bottom,
                        radiusX = borderRadiusPx,
                        radiusY = borderRadiusPx,
                        paint = paint
                    )
                } else {
                    canvas.drawRect(
                        left = left,
                        top = top,
                        right = right,
                        bottom = bottom,
                        paint = paint
                    )
                }

                if (needsRestore) {
                    canvas.restore()
                }
            }
        }
    }
)
