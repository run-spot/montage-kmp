package com.wanted.android.wanted.design.actions.button.textbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.wanted.android.wanted.design.actions.button.view.WantedButtonSideIcon
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.loading.loading.WantedCircularProgressIndicator
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.ButtonSize
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant
import com.wanted.android.wanted.design.util.OPACITY_12
import com.wanted.android.wanted.design.util.clickOnce
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun WantedTextButton(
    text: String,
    modifier: Modifier = Modifier,
    color: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.MEDIUM,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIconResource: DrawableResource? = null,
    trailingIconResource: DrawableResource? = null,
    onClick: () -> Unit = {},
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(
        variant = ButtonVariant.TEXT,
        type = color,
        size = size,
        enabled = enabled
    )
) {
    WantedTextButtonWithSlots(
        text = text,
        modifier = modifier,
        color = color,
        size = size,
        enabled = enabled,
        isLoading = isLoading,
        leadingContent = leadingIconResource?.let { resource ->
            {
                WantedButtonSideIcon(
                    modifier = Modifier
                        .buttonDrawableSize(
                            variant = ButtonVariant.TEXT,
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
                            variant = ButtonVariant.TEXT,
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
internal fun WantedTextButtonWithSlots(
    text: String,
    modifier: Modifier = Modifier,
    color: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.MEDIUM,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(
        variant = ButtonVariant.TEXT,
        type = color,
        size = size,
        enabled = enabled
    )
) {
    WantedTouchArea(
        modifier = modifier,
        verticalPadding = 4.dp,
        horizontalPadding = if (size == ButtonSize.SMALL) 6.dp else 7.dp,
        shape = RoundedCornerShape(6.dp),
        enabled = enabled,
        rippleColor = if (color == ButtonType.PRIMARY) {
            buttonDefault.contentColor.copy(alpha = OPACITY_12)
        } else {
            DesignSystemTheme.colorsOpacity.labelNormalOpacity12
        },
        content = {
            WantedTextContent(
                text = text,
                modifier = Modifier,
                type = color,
                size = size,
                enabled = enabled,
                isLoading = isLoading,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
                buttonDefault = buttonDefault
            )
        },
        onClick = {
            if (!isLoading) {
                onClick.clickOnce()
            }
        }
    )
}

@Composable
private fun WantedTextContent(
    text: String,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.PRIMARY,
    size: ButtonSize = ButtonSize.MEDIUM,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    buttonDefault: WantedButtonDefault = WantedButtonDefaults.getDefault(
        variant = ButtonVariant.TEXT,
        type = type,
        size = size,
        enabled = enabled
    )
) {
    val textColor by remember(buttonDefault.enabled, buttonDefault.contentColor) {
        mutableStateOf(buttonDefault.contentColor)
    }
    val rightIconTintColor by remember(buttonDefault.enabled, buttonDefault.rightIconTintColor) {
        mutableStateOf(buttonDefault.rightIconTintColor)
    }
    val leftIconTintColor by remember(buttonDefault.enabled, buttonDefault.leftIconTintColor) {
        mutableStateOf(buttonDefault.leftIconTintColor)
    }

    WantedTextButtonLayout(
        modifier = modifier
            .buttonHeight(ButtonVariant.TEXT, buttonDefault.size)
            .buttonWidth(buttonDefault.size, text.isEmpty())
            .buttonVerticalPadding(text.isNotEmpty())
            .buttonHorizontalPadding(
                ButtonVariant.TEXT,
                buttonDefault.size,
                text.isEmpty()
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = when (size) {
                ButtonSize.SMALL -> 4.dp
                else -> 5.dp
            },
            alignment = Alignment.CenterHorizontally
        ),
        leftDrawable = leadingContent,
        text = {
            Text(
                text = text,
                modifier = Modifier
                    .wrapContentHeight()
                    .alpha(if (isLoading) 0f else 1f),
                style = buttonDefault.textStyle,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
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
