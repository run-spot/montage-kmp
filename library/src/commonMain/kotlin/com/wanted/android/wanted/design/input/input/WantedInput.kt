package com.wanted.android.wanted.design.input.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.wanted.android.wanted.design.input.input.WantedInputDefaults.WantedInputSize
import com.wanted.android.wanted.design.input.input.WantedInputDefaults.WantedInputVariant
import com.wanted.android.wanted.design.input.input.control.CheckBoxSize
import com.wanted.android.wanted.design.input.input.control.CheckBoxState
import com.wanted.android.wanted.design.input.input.control.CheckBoxStyle
import com.wanted.android.wanted.design.input.input.control.WantedCheckBox
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.clickOnce
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.util.DevicePreviews

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

@Composable
fun WantedInput(
    modifier: Modifier = Modifier,
    label: String = "",
    variant: WantedInputVariant = WantedInputVariant.CheckBox,
    size: WantedInputSize = WantedInputSize.Medium,
    checkBoxState: CheckBoxState = CheckBoxState.Unchecked,
    bold: Boolean = false,
    enabled: Boolean = true,
    tight: Boolean = false,
    textStyle: TextStyle = if (size == WantedInputSize.Medium) {
        if (bold) {
            DesignSystemTheme.typography.body2Bold
        } else {
            DesignSystemTheme.typography.body2Regular
        }
    } else {
        if (bold) {
            DesignSystemTheme.typography.label1Bold
        } else {
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
    onCheckedChange: ((Boolean) -> Unit) = {}
) {
    val density = LocalDensity.current
    val checkBoxInteractionSource: MutableInteractionSource =
        remember { MutableInteractionSource() }

    WantedInput(
        modifier = modifier,
        label = label,
        size = size,
        bold = bold,
        enabled = enabled,
        tight = tight,
        textStyle = textStyle,
        interactionSource = interactionSource,
        leadingIcon = {
            WantedCheckBox(
                modifier = Modifier,
                size = if (size == WantedInputSize.Medium) {
                    CheckBoxSize.Normal
                } else {
                    CheckBoxSize.Small
                },
                style = when (variant) {
                    WantedInputVariant.CheckBox -> CheckBoxStyle.CheckBox
                    WantedInputVariant.Radio -> CheckBoxStyle.Radio
                    WantedInputVariant.CheckMark -> CheckBoxStyle.Check
                    WantedInputVariant.Switch -> CheckBoxStyle.Switch
                },
                checkState = checkBoxState,
                tight = tight,
                enabled = enabled,
                interactionSource = checkBoxInteractionSource,
                onCheckedChange = onCheckedChange
            )
        },
        onClick = {
            onCheckedChange(checkBoxState == CheckBoxState.Unchecked)

            val offset = with(density) {
                (if (size == WantedInputSize.Medium) 16.dp else 12.dp).toPx()
            }

            val press = PressInteraction.Press(Offset(offset, offset))
            checkBoxInteractionSource.tryEmit(press)
            checkBoxInteractionSource.tryEmit(PressInteraction.Release(press))
        }
    )
}

@DevicePreviews
@Composable
private fun WantedInputPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Text(text = "CheckBox Small")
                WantedInput(WantedInputSize.Small, WantedInputVariant.CheckBox)

                Text(text = "CheckBox Normal")
                WantedInput(WantedInputSize.Medium, WantedInputVariant.CheckBox)

                Text(text = "Radio Small")
                WantedInput(WantedInputSize.Small, WantedInputVariant.Radio)

                Text(text = "Radio Normal")
                WantedInput(WantedInputSize.Medium, WantedInputVariant.Radio)

                Text(text = "NestedCheckBox Small")
                WantedInput(WantedInputSize.Small, WantedInputVariant.CheckMark)

                Text(text = "NestedCheckBox Normal")
                WantedInput(WantedInputSize.Medium, WantedInputVariant.CheckMark)
            }
        }
    }
}

@Composable
private fun WantedInput(
    size: WantedInputSize,
    variant: WantedInputVariant
) {
    var checked by remember { mutableStateOf(CheckBoxState.Unchecked) }

    WantedInput(
        modifier = Modifier,
        label = "${size.name}\n${size.name}",
        size = size,
        checkBoxState = checked,
        bold = false,
        enabled = true,
        variant = variant,
        onCheckedChange = {
            checked = if (it) CheckBoxState.Checked else CheckBoxState.Unchecked
        }
    )

    WantedInput(
        modifier = Modifier,
        label = size.name,
        size = size,
        checkBoxState = checked,
        bold = false,
        enabled = false,
        variant = variant,
        onCheckedChange = {
            checked = if (it) CheckBoxState.Checked else CheckBoxState.Unchecked
        }
    )

    WantedInput(
        modifier = Modifier,
        label = size.name,
        size = size,
        checkBoxState = checked,
        bold = true,
        enabled = true,
        variant = variant,
        onCheckedChange = {
            checked = if (it) CheckBoxState.Checked else CheckBoxState.Unchecked
        }
    )

    WantedInput(
        modifier = Modifier,
        label = size.name,
        size = size,
        checkBoxState = checked,
        bold = true,
        enabled = false,
        variant = variant,
        onCheckedChange = {
            checked = if (it) CheckBoxState.Checked else CheckBoxState.Unchecked
        }
    )
}
