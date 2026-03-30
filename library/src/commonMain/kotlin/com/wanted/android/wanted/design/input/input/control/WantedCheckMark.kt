package com.wanted.android.wanted.design.input.input.control

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_check
import com.wanted.android.wanted.design.resources.icon_normal_check_thick
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_43
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedCheckMark(
    size: CheckBoxSize,
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tight: Boolean = false,
    thick: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit = {}
) {
    WantedTouchArea(
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        horizontalPadding = if (tight) 6.dp else 4.dp,
        verticalPadding = 4.dp,
        interactionSource = interactionSource,
        content = {
            Image(
                modifier = Modifier
                    .height(height = if (size == CheckBoxSize.Small) 20.dp else 24.dp)
                    .width(
                        width = when {
                            tight -> if (size == CheckBoxSize.Small) 16.dp else 20.dp
                            else -> if (size == CheckBoxSize.Small) 20.dp else 24.dp
                        }
                    )
                    .padding(horizontal = if (tight) 1.dp else 3.dp)
                    .padding(vertical = 3.dp),
                painter = if (thick) {
                    painterResource(Res.drawable.icon_normal_check_thick)
                } else {
                    painterResource(Res.drawable.icon_normal_check)
                },
                contentDescription = "checkBox_check",
                colorFilter = ColorFilter.tint(
                    color = if (checked) {
                        if (enabled) {
                            DesignSystemTheme.colors.primaryNormal
                        } else {
                            DesignSystemTheme.colors.primaryNormal.copy(OPACITY_43)
                        }
                    } else {
                        if (enabled) {
                            DesignSystemTheme.colors.labelAssistive
                        } else {
                            DesignSystemTheme.colors.labelAssistive.copy(0.13f)
                        }
                    }
                )
            )
        }
    ) {
        onCheckedChange(!checked)
    }
}
