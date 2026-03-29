package com.wanted.android.wanted.design.loading.skeleton

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import com.wanted.android.wanted.design.theme.DesignSystemTheme

fun Modifier.shimmer(
    color: Color = Color.Unspecified,
): Modifier = composed {
    val shimmerColor = if (color == Color.Unspecified) {
        DesignSystemTheme.colors.backgroundNormalNormal
    } else {
        color
    }

    val transition = rememberInfiniteTransition(label = "")
    val alphaAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0.66f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = ANIMATION_DURATION,
                easing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
            ),
            repeatMode = RepeatMode.Reverse // Reverse로 깜박이는 효과 추가
        ), label = ""
    )

    drawWithContent {
        drawContent()
        drawRect(color = shimmerColor.copy(alpha = alphaAnimation))
    }
}

private const val ANIMATION_DURATION = 1000
