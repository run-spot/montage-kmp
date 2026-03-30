package com.wanted.android.wanted.design.input.textinput.autocompletetextfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.textinput.textfield.WantedTextField
import com.wanted.android.wanted.design.input.textinput.textfield.WantedTextFieldDefaults
import com.wanted.android.wanted.design.presentation.autocomplete.WantedAutoComplete
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WantedAutoCompleteTextField(
    text: String,
    sectionItemCount: (section: Int) -> Int,
    onExpandedChange: (Boolean) -> Unit,
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
    requiredBadge: Boolean = false,
    expanded: Boolean = false,
    anchorPadding: Dp = 0.dp,
    dropDownMaxHeight: Dp = 200.dp,
    sectionTitleHorizontalPadding: Dp = 20.dp,
    sectionCount: Int = 1,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    sectionTitle: ((section: Int) -> String)? = null,
    onClickRightButton: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    topDirectInput: @Composable (() -> Unit)? = null,
    bottomDirectInput: @Composable (() -> Unit)? = null,
    sectionItem: @Composable (section: Int, index: Int) -> Unit
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { onExpandedChange(it && text.isNotEmpty()) }
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            WantedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = MenuAnchorType.PrimaryEditable, enabled = true),
                title = title,
                text = text,
                description = description,
                requiredBadge = requiredBadge,
                status = status,
                enabled = enabled,
                rightButtonEnabled = rightButtonEnabled,
                maxLines = maxLines,
                minLines = minLines,
                maxWordCount = maxWordCount,
                interactionSource = interactionSource,
                focusRequester = focusRequester,
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
                onValueChange = { changed ->
                    onExpandedChange(changed.isNotEmpty())
                    onValueChange(changed)
                },
            )

            WantedAutoComplete(
                modifier = Modifier
                    .width(maxWidth)
                    .heightIn(max = dropDownMaxHeight),
                anchorPadding = anchorPadding,
                containerColor = DesignSystemTheme.colors.backgroundNormalNormal,
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                sectionTitleHorizontalPadding = sectionTitleHorizontalPadding,
                sectionCount = sectionCount,
                sectionTitle = sectionTitle,
                sectionItemCount = sectionItemCount,
                sectionItem = sectionItem,
                topDirectInput = topDirectInput,
                bottomDirectInput = bottomDirectInput
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WantedAutoCompleteTextField(
    value: TextFieldValue,
    sectionItemCount: (section: Int) -> Int,
    sectionItem: @Composable (section: Int, index: Int) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
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
    requiredBadge: Boolean = false,
    expanded: Boolean = false,
    anchorPadding: Dp = 0.dp,
    dropDownMaxHeight: Dp = 200.dp,
    sectionTitleHorizontalPadding: Dp = 20.dp,
    sectionCount: Int = 1,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    sectionTitle: ((section: Int) -> String)? = null,
    onClickRightButton: () -> Unit = {},
    onValueChange: (TextFieldValue) -> Unit = {},
    topDirectInput: @Composable (() -> Unit)? = null,
    bottomDirectInput: @Composable (() -> Unit)? = null
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { onExpandedChange(it && value.text.isNotEmpty()) }
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            WantedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = MenuAnchorType.PrimaryEditable, enabled = true),
                title = title,
                value = value,
                description = description,
                requiredBadge = requiredBadge,
                status = status,
                enabled = enabled,
                rightButtonEnabled = rightButtonEnabled,
                maxLines = maxLines,
                minLines = minLines,
                maxWordCount = maxWordCount,
                interactionSource = interactionSource,
                focusRequester = focusRequester,
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
                onValueChange = { changed ->
                    onExpandedChange(changed.text.isNotEmpty())
                    onValueChange(changed)
                },
            )

            WantedAutoComplete(
                modifier = Modifier
                    .width(maxWidth)
                    .heightIn(max = dropDownMaxHeight),
                anchorPadding = anchorPadding,
                containerColor = DesignSystemTheme.colors.backgroundNormalNormal,
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                sectionTitleHorizontalPadding = sectionTitleHorizontalPadding,
                sectionCount = sectionCount,
                sectionTitle = sectionTitle,
                sectionItemCount = sectionItemCount,
                sectionItem = sectionItem,
                topDirectInput = topDirectInput,
                bottomDirectInput = bottomDirectInput
            )
        }
    }
}

@DevicePreviews
@Composable
private fun WantedAutoCompleteTextInputPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                WantedAutoCompleteTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ㅁㄴㅇ",
                    placeholder = "텍스트를 입력해 주세요.",
                    rightButton = "텍스트",
                    expanded = false,
                    onValueChange = {},
                    onClickRightButton = {},
                    onExpandedChange = {},
                    sectionItem = { _, _ -> },
                    sectionItemCount = { 0 }
                )
            }
        }
    }
}
