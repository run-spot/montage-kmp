package com.wanted.android.wanted.design.input.framedstyle

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowStyle
import com.wanted.android.wanted.design.base.wantedDropShadow
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_43

fun Modifier.framedStyle(
    status: WantedFramedStyleStatus = WantedFramedStyleStatus.Normal,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    enabled: Boolean = true,
    shadow: WantedShadowStyle = WantedShadowStyle.XSmall(),
) = composed {
    wantedDropShadow(shadow)
        .border(
            shape = shape,
            color = when {
                status == WantedFramedStyleStatus.Negative || status == WantedFramedStyleStatus.Selected -> {
                    if (enabled) {
                        DesignSystemTheme.colors.backgroundNormalNormal.copy(alpha = OPACITY_43)
                    } else {
                        DesignSystemTheme.colors.backgroundNormalNormal.copy(alpha = 0.185f)
                    }
                }

                else -> DesignSystemTheme.colors.transparent
            },
            width = if (status == WantedFramedStyleStatus.Selected) 2.dp else 1.dp
        )
        .border(
            shape = shape,
            color = when (status) {
                WantedFramedStyleStatus.Negative -> {
                    if (enabled) {
                        DesignSystemTheme.colors.statusNegative.copy(OPACITY_43)
                    } else {
                        DesignSystemTheme.colors.statusNegative.copy(0.185f)
                    }
                }

                WantedFramedStyleStatus.Selected -> {
                    if (enabled) {
                        DesignSystemTheme.colors.primaryNormal.copy(OPACITY_43)
                    } else {
                        DesignSystemTheme.colors.primaryNormal.copy(0.185f)
                    }
                }

                else -> DesignSystemTheme.colors.lineNormalNeutral
            },
            width = if (status == WantedFramedStyleStatus.Selected) 2.dp else 1.dp
        )
        .clip(shape)
}

enum class WantedFramedStyleStatus {
    Normal,
    Negative,
    Selected
}
