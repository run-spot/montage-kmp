package com.wanted.android.wanted.design.input.textinput.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.textinput.view.ComponentTitle
import com.wanted.android.wanted.design.input.textinput.view.WantedCustomTextField
import com.wanted.android.wanted.design.input.textinput.view.WantedTextInputLayout
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_check
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedTextField(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    title: String = "",
    description: String? = null,
    rightButton: String? = null,
    rightButtonVariant: WantedTextFieldDefaults.RightVariant = WantedTextFieldDefaults.RightVariant.Normal,
    status: WantedTextFieldDefaults.Status = WantedTextFieldDefaults.Status.Normal,
    enabled: Boolean = true,
    rightButtonEnabled: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    maxWordCount: Int = 2000,
    enabledOverflowText: Boolean = false,
    requiredBadge: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClickRightButton: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = text)) }
    val textFieldValue = textFieldValueState.copy(text = text)

    SideEffect {
        if (
            textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    var lastTextValue by remember(text) { mutableStateOf(text) }

    WantedTextInputLayout(
        modifier = modifier,
        title = if (title.isNotEmpty()) {
            { ComponentTitle(title = title, isRequiredBadge = requiredBadge) }
        } else {
            null
        },
        textField = {
            WantedCustomTextField(
                value = textFieldValue,
                error = status == WantedTextFieldDefaults.Status.Negative,
                enabled = enabled,
                rightButtonEnabled = rightButtonEnabled,
                complete = status == WantedTextFieldDefaults.Status.Positive,
                maxLines = maxLines,
                minLines = minLines,
                maxWordCount = maxWordCount,
                enabledOverflowText = enabledOverflowText,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                background = background,
                visualTransformation = visualTransformation,
                rightButton = rightButton,
                rightButtonVariant = rightButtonVariant,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                trailingContent = trailingContent,
                onClickRightButton = onClickRightButton,
                onValueChange = { newValue ->
                    textFieldValueState = newValue
                    val changed = lastTextValue != newValue.text
                    lastTextValue = newValue.text
                    if (changed) onValueChange(newValue.text)
                },
                focusRequester = focusRequester
            )
        },
        message = description?.let {
            {
                Text(
                    text = description,
                    style = DesignSystemTheme.typography.caption1Regular,
                    color = if (enabled && status == WantedTextFieldDefaults.Status.Negative) {
                        DesignSystemTheme.colors.statusNegative
                    } else {
                        DesignSystemTheme.colors.labelAlternative
                    },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}

@Composable
fun WantedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String = "",
    requiredBadge: Boolean = false,
    placeholder: String = "",
    description: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    rightButton: String? = null,
    onClickRightButton: () -> Unit = {},
    rightButtonEnabled: Boolean = true,
    rightButtonVariant: WantedTextFieldDefaults.RightVariant = WantedTextFieldDefaults.RightVariant.Normal,
    status: WantedTextFieldDefaults.Status = WantedTextFieldDefaults.Status.Normal,
    maxWordCount: Int = 2000,
    enabledOverflowText: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    WantedTextInputLayout(
        modifier = modifier,
        title = if (title.isNotEmpty()) {
            { ComponentTitle(title = title, isRequiredBadge = requiredBadge) }
        } else {
            null
        },
        textField = {
            WantedCustomTextField(
                value = value,
                error = status == WantedTextFieldDefaults.Status.Negative,
                enabled = enabled,
                rightButtonEnabled = rightButtonEnabled,
                complete = status == WantedTextFieldDefaults.Status.Positive,
                maxLines = maxLines,
                minLines = minLines,
                maxWordCount = maxWordCount,
                enabledOverflowText = enabledOverflowText,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                background = background,
                visualTransformation = visualTransformation,
                rightButton = rightButton,
                rightButtonVariant = rightButtonVariant,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                trailingContent = trailingContent,
                onClickRightButton = onClickRightButton,
                onValueChange = onValueChange,
                focusRequester = focusRequester
            )
        },
        message = description?.let {
            {
                Text(
                    text = description,
                    style = DesignSystemTheme.typography.caption1Regular,
                    color = if (enabled && status == WantedTextFieldDefaults.Status.Negative) {
                        DesignSystemTheme.colors.statusNegative
                    } else {
                        DesignSystemTheme.colors.labelAlternative
                    },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}

@DevicePreviews
@Composable
private fun WantedTextInputPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedTextField(
                    text = "텍스트를 입력해 주세요.",
                    placeholder = "텍스트를 입력해 주세요.",
                    rightButton = "텍스트",
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.icon_normal_circle_check),
                            contentDescription = ""
                        )
                    }
                )
                WantedTextField(
                    text = "텍스트를 입력해 주세요.",
                    placeholder = "텍스트를 입력해 주세요.",
                    rightButton = "텍스트",
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.icon_normal_circle_check_fill),
                            tint = DesignSystemTheme.colors.primaryNormal,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}
