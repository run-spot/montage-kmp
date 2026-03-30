package com.wanted.android.wanted.design.input.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.wanted.android.wanted.design.input.input.WantedInputDefaults.WantedInputSize
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.clickOnce

@Composable
fun WantedInput(
    modifier: Modifier = Modifier,
    label: String = "",
    size: WantedInputSize = WantedInputSize.Medium,
    bold: Boolean = false,
    enabled: Boolean = true,
    tight: Boolean = false,
    textStyle: TextStyle = when {
        bold && size == WantedInputSize.Medium -> {
            DesignSystemTheme.typography.label1Bold
        }

        bold && size == WantedInputSize.Small -> {
            DesignSystemTheme.typography.caption1Medium
        }

        else -> {
            DesignSystemTheme.typography.label1Regular
        }
    }.copy(
        color = if (enabled) {
            DesignSystemTheme.colors.labelNormal
        } else {
            DesignSystemTheme.colors.labelDisable
        }
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    WantedInput(
        modifier = modifier,
        size = size,
        tight = tight,
        textStyle = textStyle,
        interactionSource = interactionSource,
        leadingIcon = leadingIcon,
        label = {
            Text(text = label)
        },
        onClick = onClick
    )
}

@Composable
fun WantedInput(
    modifier: Modifier = Modifier,
    size: WantedInputSize = WantedInputSize.Medium,
    tight: Boolean = false,
    textStyle: TextStyle,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    ProvideTextStyle(value = textStyle) {
        WantedInputLayout(
            modifier = modifier.clickOnce(
                enabled = true,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
            size = size,
            tight = tight,
            textStyle = textStyle,
            leadingIcon = leadingIcon,
            label = label
        )
    }
}
