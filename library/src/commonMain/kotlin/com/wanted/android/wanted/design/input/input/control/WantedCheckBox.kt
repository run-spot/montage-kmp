package com.wanted.android.wanted.design.input.input.control

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_check
import com.wanted.android.wanted.design.resources.icon_normal_check_thick
import com.wanted.android.wanted.design.resources.icon_normal_line_horizontal
import com.wanted.android.wanted.design.resources.icon_normal_line_horizontal_thick
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_43
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun WantedCheckBox(
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: CheckBoxSize = CheckBoxSize.Normal,
    style: CheckBoxStyle = CheckBoxStyle.CheckBox,
    checkState: CheckBoxState = CheckBoxState.Unchecked,
    tight: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    when (style) {
        CheckBoxStyle.CheckBox -> {
            WantedCheckBoxImpl(
                modifier = modifier,
                size = size,
                shape = RoundedCornerShape(5.dp),
                checkState = checkState,
                tight = tight,
                enabled = enabled,
                interactionSource = interactionSource,
                onCheckedChange = onCheckedChange
            )
        }

        CheckBoxStyle.RoundCheckBox -> {
            WantedCheckBoxImpl(
                modifier = modifier,
                size = size,
                shape = CircleShape,
                checkState = checkState,
                tight = tight,
                enabled = enabled,
                interactionSource = interactionSource,
                onCheckedChange = onCheckedChange
            )
        }

        CheckBoxStyle.Check -> {
            WantedCheckMark(
                modifier = modifier,
                size = size,
                checked = checkState != CheckBoxState.Unchecked,
                enabled = enabled,
                tight = tight,
                interactionSource = interactionSource,
                onCheckedChange = onCheckedChange
            )
        }

        CheckBoxStyle.Radio -> {
            WantedRadioButton(
                modifier = modifier,
                size = size,
                checked = checkState != CheckBoxState.Unchecked,
                enabled = enabled,
                tight = tight,
                interactionSource = interactionSource,
                onCheckedChange = onCheckedChange
            )
        }

        CheckBoxStyle.Switch -> {
            WantedSwitch(
                modifier = modifier,
                size = size,
                checked = checkState != CheckBoxState.Unchecked,
                enabled = enabled,
                interactionSource = interactionSource,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
private fun WantedCheckBoxImpl(
    modifier: Modifier,
    size: CheckBoxSize,
    shape: Shape,
    checkState: CheckBoxState,
    tight: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit) = {},
) {
    WantedTouchArea(
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        horizontalPadding = if (tight) 6.dp else 4.dp,
        verticalPadding = 4.dp,
        interactionSource = interactionSource,
        content = {
            Box(
                modifier = Modifier
                    .height(height = if (size == CheckBoxSize.Small) 20.dp else 24.dp)
                    .width(
                        width = when {
                            tight -> if (size == CheckBoxSize.Small) 16.dp else 20.dp
                            else -> if (size == CheckBoxSize.Small) 20.dp else 24.dp
                        }
                    )
                    .padding(
                        horizontal = when {
                            tight -> if (size == CheckBoxSize.Small) 0.dp else 1.dp
                            else -> if (size == CheckBoxSize.Small) 2.dp else 3.dp
                        }
                    )
                    .padding(vertical = if (size == CheckBoxSize.Small) 2.dp else 3.dp)
                    .clip(shape)
                    .border(
                        width = 1.5.dp,
                        color = when {
                            enabled && checkState == CheckBoxState.Unchecked -> {
                                DesignSystemTheme.colors.lineNormalNormal
                            }

                            !enabled && checkState == CheckBoxState.Unchecked -> {
                                DesignSystemTheme.colors.lineNormalNormal.copy(0.1f)
                            }

                            else -> DesignSystemTheme.colors.transparent
                        },
                        shape = shape
                    )
                    .background(
                        color = when {
                            checkState == CheckBoxState.Unchecked -> DesignSystemTheme.colors.transparent
                            enabled -> DesignSystemTheme.colors.primaryNormal
                            else -> DesignSystemTheme.colors.primaryNormal.copy(OPACITY_43)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (checkState != CheckBoxState.Unchecked) {
                    Image(
                        modifier = Modifier
                            .size(if (size == CheckBoxSize.Small) 16.dp else 18.dp)
                            .padding(2.dp),
                        painter = painterResource(
                            if (checkState == CheckBoxState.Indeterminate) {
                                Res.drawable.icon_normal_line_horizontal_thick
                            } else {
                                Res.drawable.icon_normal_check_thick
                            }
                        ),
                        contentDescription = "checkBox_check",
                        colorFilter = ColorFilter.tint(
                            color = DesignSystemTheme.colors.staticWhite
                        )
                    )
                }
            }
        }
    ) {
        onCheckedChange(checkState == CheckBoxState.Unchecked)
    }
}

@Deprecated(
    "Use WantedCheckBox(modifier, size, style, checkState, enabled, interactionSource, onCheckedChange)"
)
@Composable
fun WantedCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    isIndeterminate: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors(
        uncheckedColor = DesignSystemTheme.colors.lineNormalNeutral,
        checkedColor = DesignSystemTheme.colors.primaryNormal,
        checkmarkColor = DesignSystemTheme.colors.staticWhite
    ),
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val toggleState = when {
        !checked -> ToggleableState.Off
        isIndeterminate -> ToggleableState.Indeterminate
        else -> ToggleableState.On
    }

    val toggleableModifier = Modifier.triStateToggleable(
        state = toggleState,
        onClick = { onCheckedChange(!checked) },
        enabled = enabled,
        role = Role.Checkbox,
        interactionSource = interactionSource,
        indication = ripple(bounded = false)
    )

    CheckboxImpl(
        enabled = enabled,
        value = toggleState,
        modifier = modifier.then(toggleableModifier),
        colors = colors
    )
}

@Composable
private fun CheckboxImpl(
    modifier: Modifier,
    enabled: Boolean,
    value: ToggleableState,
    colors: CheckboxColors,
) {
    val borderColor = colors.borderColor(enabled = enabled, state = value).value
    val boxColor = colors.boxColor(enabled = enabled, state = value).value
    val checkmarkColor = colors.checkmarkColor(state = value).value

    Box(
        modifier = Modifier
            .padding(3.dp)
            .defaultMinSize(18.dp)
            .width(intrinsicSize = IntrinsicSize.Min)
            .height(intrinsicSize = IntrinsicSize.Min)
            .clip(RoundedCornerShape(3.dp))
            .then(modifier)
            .border(
                width = 2.dp,
                color = borderColor,
                RoundedCornerShape(3.dp)
            )
            .background(boxColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            painter = painterResource(
                if (value == ToggleableState.Indeterminate) {
                    Res.drawable.icon_normal_line_horizontal
                } else {
                    Res.drawable.icon_normal_check
                }
            ),
            contentDescription = "checkBox_check",
            colorFilter = ColorFilter.tint(color = checkmarkColor)
        )
    }
}
