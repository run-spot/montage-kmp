package com.wanted.android.wanted.design.actions.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.actions.button.config.WantedButtonDefault
import com.wanted.android.wanted.design.actions.button.config.WantedButtonDefaults
import com.wanted.android.wanted.design.actions.button.config.buttonDrawableSize
import com.wanted.android.wanted.design.actions.button.config.buttonHeight
import com.wanted.android.wanted.design.actions.button.config.buttonHorizontalPadding
import com.wanted.android.wanted.design.actions.button.config.buttonVerticalPadding
import com.wanted.android.wanted.design.actions.button.config.buttonWidth
import com.wanted.android.wanted.design.actions.button.view.WantedButtonLayout
import com.wanted.android.wanted.design.actions.button.view.WantedButtonSideIcon
import com.wanted.android.wanted.design.loading.loading.WantedCircularProgressIndicator
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.ButtonSize
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant
import com.wanted.android.wanted.design.util.clickOnce
import com.wanted.android.wanted.design.util.wantedRippleEffect
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun WantedOutlinedButton(
    modifier: Modifier = Modifier,
    text: String = "",
    type: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.LARGE,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIconResource: DrawableResource? = null,
    trailingIconResource: DrawableResource? = null,
    onClick: () -> Unit = {},
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(
        variant = ButtonVariant.OUTLINED,
        type = type,
        size = size,
        enabled = enabled
    )
) {
    WantedOutlinedButtonWithSlots(
        modifier = modifier,
        text = text,
        type = type,
        size = size,
        enabled = enabled,
        isLoading = isLoading,
        leadingContent = leadingIconResource?.let { resource ->
            {
                WantedButtonSideIcon(
                    modifier = Modifier
                        .buttonDrawableSize(
                            variant = ButtonVariant.OUTLINED,
                            size = buttonDefault.size
                        )
                        .alpha(if (isLoading) 0f else 1f),
                    resource = resource,
                    tint = buttonDefault.leftIconTintColor
                )
            }
        },
        trailingContent = trailingIconResource?.let { resource ->
            {
                WantedButtonSideIcon(
                    modifier = Modifier
                        .buttonDrawableSize(
                            variant = ButtonVariant.OUTLINED,
                            size = buttonDefault.size
                        )
                        .alpha(if (isLoading) 0f else 1f),
                    resource = resource,
                    tint = buttonDefault.rightIconTintColor
                )
            }
        },
        onClick = onClick,
        buttonDefault = buttonDefault
    )
}

@Composable
internal fun WantedOutlinedButtonWithSlots(
    modifier: Modifier = Modifier,
    text: String = "",
    type: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.LARGE,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(
        variant = ButtonVariant.OUTLINED,
        type = type,
        size = size,
        enabled = enabled
    )
) {
    WantedButtonLayout(
        modifier = modifier
            .clip(buttonDefault.borderShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = wantedRippleEffect(
                    DesignSystemTheme.colorsOpacity.labelNormalOpacity12
                ),
                enabled = enabled,
                onClick = {
                    if (!isLoading) {
                        onClick.clickOnce()
                    }
                }
            )
            .border(
                BorderStroke(1.dp, buttonDefault.borderColor),
                buttonDefault.borderShape
            )
            .background(buttonDefault.backgroundColor)
            .buttonHeight(ButtonVariant.OUTLINED, buttonDefault.size)
            .buttonWidth(buttonDefault.size, text.isEmpty())
            .buttonVerticalPadding(text.isNotEmpty())
            .buttonHorizontalPadding(ButtonVariant.OUTLINED, buttonDefault.size, text.isEmpty()),
        horizontalArrangement = Arrangement.spacedBy(
            space = when (size) {
                ButtonSize.LARGE -> 6.dp
                ButtonSize.MEDIUM -> 5.dp
                ButtonSize.SMALL -> 4.dp
            },
            alignment = Alignment.CenterHorizontally
        ),
        leftDrawable = leadingContent,
        text = if (text.isNotEmpty()) {
            {
                Text(
                    text = text,
                    modifier = Modifier
                        .wrapContentHeight()
                        .alpha(if (isLoading) 0f else 1f),
                    style = buttonDefault.textStyle,
                    color = buttonDefault.contentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            null
        },
        rightDrawable = trailingContent,
        loading = if (isLoading) {
            {
                WantedCircularProgressIndicator(
                    modifier = Modifier.size(buttonDefault.loadingSize),
                    color = buttonDefault.loadingColor
                )
            }
        } else {
            null
        }
    )
}
