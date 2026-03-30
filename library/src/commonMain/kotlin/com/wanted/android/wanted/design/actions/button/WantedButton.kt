package com.wanted.android.wanted.design.actions.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wanted.android.wanted.design.actions.button.config.WantedButtonDefault
import com.wanted.android.wanted.design.actions.button.config.WantedButtonDefaults
import com.wanted.android.wanted.design.actions.button.textbutton.WantedTextButton
import com.wanted.android.wanted.design.actions.button.textbutton.WantedTextButtonWithSlots
import com.wanted.android.wanted.design.util.ButtonSize
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun WantedButton(
    text: String,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.LARGE,
    variant: ButtonVariant = ButtonVariant.SOLID,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIconResource: DrawableResource? = null,
    trailingIconResource: DrawableResource? = null,
    onClick: () -> Unit = {}
) {
    when (variant) {
        ButtonVariant.SOLID -> {
            WantedSolidButton(
                modifier = modifier,
                text = text,
                type = type,
                size = size,
                enabled = enabled,
                isLoading = isLoading,
                buttonDefault = WantedButtonDefaults.getDefault(
                    variant = variant,
                    type = type,
                    enabled = enabled,
                    size = size
                ),
                leadingIconResource = leadingIconResource,
                trailingIconResource = trailingIconResource,
                onClick = onClick,
            )
        }

        ButtonVariant.OUTLINED -> {
            WantedOutlinedButton(
                modifier = modifier,
                text = text,
                size = size,
                type = type,
                enabled = enabled,
                isLoading = isLoading,
                buttonDefault = WantedButtonDefaults.getDefault(
                    variant = variant,
                    type = type,
                    enabled = enabled,
                    size = size
                ),
                leadingIconResource = leadingIconResource,
                trailingIconResource = trailingIconResource,
                onClick = onClick,
            )
        }

        ButtonVariant.TEXT -> {
            WantedTextButton(
                modifier = modifier,
                text = text,
                size = size,
                color = type,
                enabled = enabled,
                isLoading = isLoading,
                buttonDefault = WantedButtonDefaults.getDefault(
                    variant = variant,
                    type = type,
                    enabled = enabled,
                    size = size
                ),
                leadingIconResource = leadingIconResource,
                trailingIconResource = trailingIconResource,
                onClick = onClick,
            )
        }
    }
}

@Composable
fun WantedButton(
    text: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(),
    onClick: () -> Unit = {}
) {
    when (buttonDefault.variant) {
        ButtonVariant.SOLID -> {
            WantedSolidButtonWithSlots(
                modifier = modifier,
                text = text,
                isLoading = isLoading,
                enabled = buttonDefault.enabled,
                buttonDefault = buttonDefault,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
                onClick = onClick,
            )
        }

        ButtonVariant.OUTLINED -> {
            WantedOutlinedButtonWithSlots(
                modifier = modifier,
                text = text,
                isLoading = isLoading,
                enabled = buttonDefault.enabled,
                buttonDefault = buttonDefault,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
                onClick = onClick,
            )
        }

        ButtonVariant.TEXT -> {
            WantedTextButtonWithSlots(
                modifier = modifier,
                text = text,
                isLoading = isLoading,
                enabled = buttonDefault.enabled,
                buttonDefault = buttonDefault,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
                onClick = onClick,
            )
        }
    }
}
