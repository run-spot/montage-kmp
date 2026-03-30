package com.wanted.android.wanted.design.input.input.control

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.ic_normal_round_checkbox_checked_svg
import com.wanted.android.wanted.design.resources.ic_normal_round_checkbox_partial_svg
import com.wanted.android.wanted.design.resources.ic_normal_round_checkbox_unchecked_svg
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.ripple

@Deprecated("Deprecated")
@Composable
fun WantedRoundCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    isIndeterminate: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    size: CheckBoxSize = CheckBoxSize.Normal,
    onCheckedChange: (Boolean) -> Unit = {}
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

    RoundCheckboxImpl(
        value = toggleState,
        modifier = modifier.then(toggleableModifier),
        size = size
    )
}

@Composable
private fun RoundCheckboxImpl(
    modifier: Modifier,
    value: ToggleableState,
    size: CheckBoxSize
) {
    Box(
        modifier = modifier.size(size = if (size == CheckBoxSize.Normal) 24.dp else 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                when (value) {
                    ToggleableState.On -> Res.drawable.ic_normal_round_checkbox_checked_svg
                    ToggleableState.Indeterminate -> Res.drawable.ic_normal_round_checkbox_partial_svg
                    else -> Res.drawable.ic_normal_round_checkbox_unchecked_svg
                }
            ),
            contentDescription = "radio button"
        )
    }
}
