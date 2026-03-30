package com.wanted.android.wanted.design.input.input.control

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.clickOnce
import kotlinx.coroutines.launch

@Composable
internal fun WantedSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: CheckBoxSize = CheckBoxSize.Normal,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val thumbSize = remember(size) {
        if (size == CheckBoxSize.Normal) 24.dp else 18.dp
    }

    val width = remember(size) {
        if (size == CheckBoxSize.Normal) 52.dp else 39.dp
    }
    val density = LocalDensity.current

    val animatedOffsetX = remember { Animatable(0f) }
    val offset = if (!checked) {
        0f
    } else {
        with(density) {
            thumbSize.toPx() - (if (size == CheckBoxSize.Normal) 4.dp else 3.dp).toPx()
        }
    }

    val scope = rememberCoroutineScope()
    LaunchedEffect(checked) {
        scope.launch {
            animatedOffsetX.animateTo(
                targetValue = offset,
                animationSpec = tween(durationMillis = 200)
            )
        }
    }

    var isChecked by remember { mutableStateOf(checked) }

    LaunchedEffect(animatedOffsetX.isRunning) {
        if (!animatedOffsetX.isRunning) {
            isChecked = checked
        }
    }

    val toggleableModifier = Modifier.clickOnce(
        enabled = enabled,
        interactionSource = interactionSource,
        indication = null
    ) { onCheckedChange(!isChecked) }

    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.43f)
            .clip(RoundedCornerShape(32.dp))
            .background(
                if (isChecked) {
                    DesignSystemTheme.colors.primaryNormal
                } else {
                    DesignSystemTheme.colors.fillStrong
                }
            )
            .width(width)
            .height(if (size == CheckBoxSize.Normal) 32.dp else 24.dp)
            .then(toggleableModifier)
            .padding(if (size == CheckBoxSize.Normal) 4.dp else 3.dp),
    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset { IntOffset(animatedOffsetX.value.toInt(), 0) }
                .indication(
                    interactionSource = interactionSource,
                    indication = ripple(
                        bounded = false,
                        radius = thumbSize / 2
                    )
                )
                .background(DesignSystemTheme.colors.staticWhite, CircleShape),
            contentAlignment = Alignment.Center
        ) {}
    }
}
