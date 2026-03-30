package com.wanted.android.wanted.design.util

import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
fun wantedRippleEffect(
    color: Color = DesignSystemTheme.colorsOpacity.labelNormalOpacity12,
) = ripple(
    color = color,
)
