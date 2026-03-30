@file:JvmName("WantedTopAppBarAndroidKt")

package com.wanted.android.wanted.design.navigations.topbar

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.wanted.android.designsystem.R
import com.wanted.android.wanted.design.input.search.WantedSearchField
import com.wanted.android.wanted.design.input.search.WantedSearchFieldDefaults.Size
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarContract.Variant
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import kotlin.jvm.JvmName

@Composable
fun WantedSearchTopAppBar(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    backgroundColor: androidx.compose.ui.graphics.Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    scrollableState: ScrollableState? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    size: Size = Size.Medium(),
    maxWordCount: Int = Int.MAX_VALUE,
    enabledOverflowText: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focused: State<Boolean>? = null,
    textStyle: TextStyle = DesignSystemTheme.typography.body1Regular.copy(
        color = if (enabled) {
            DesignSystemTheme.colors.labelNormal
        } else {
            DesignSystemTheme.colors.labelAlternative
        }
    ),
    cursorBrush: Brush = SolidColor(textStyle.color),
    focusRequester: FocusRequester? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    onClickBack: () -> Unit = {},
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    val localInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val localFocused = focused ?: localInteractionSource.collectIsFocusedAsState()
    val localFocusRequester = focusRequester ?: remember { FocusRequester() }

    WantedTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        backgroundColor = backgroundColor,
        background = background,
        variant = Variant.Search,
        scrollableState = scrollableState,
        navigationIcon = {
            WantedTopAppBarIconButton(
                variant = Variant.Search,
                painter = painterResource(id = R.drawable.icon_normal_arrow_left),
                onClick = { onClickBack() }
            )
        },
        title = {
            WantedSearchField(
                value = value,
                placeholder = placeholder,
                size = size,
                enabled = enabled,
                maxWordCount = maxWordCount,
                enabledOverflowText = enabledOverflowText,
                interactionSource = localInteractionSource,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                focused = localFocused,
                cursorBrush = cursorBrush,
                textStyle = textStyle,
                focusRequester = localFocusRequester,
                onValueChange = onValueChange
            )
        },
        actions = actions
    )
}

@Composable
fun WantedSearchTopAppBar(
    text: String,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    backgroundColor: androidx.compose.ui.graphics.Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    scrollableState: ScrollableState? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    size: Size = Size.Small(),
    maxWordCount: Int = Int.MAX_VALUE,
    enabledOverflowText: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focused: State<Boolean>? = null,
    textStyle: TextStyle = DesignSystemTheme.typography.body1Regular.copy(
        color = if (enabled) {
            DesignSystemTheme.colors.labelNormal
        } else {
            DesignSystemTheme.colors.labelAlternative
        }
    ),
    cursorBrush: Brush = SolidColor(textStyle.color),
    focusRequester: FocusRequester? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    onClickBack: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    val localInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val localFocused = focused ?: localInteractionSource.collectIsFocusedAsState()
    val localFocusRequester = focusRequester ?: remember { FocusRequester() }

    WantedTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        backgroundColor = backgroundColor,
        background = background,
        variant = Variant.Search,
        scrollableState = scrollableState,
        navigationIcon = {
            WantedTopAppBarIconButton(
                variant = Variant.Search,
                painter = painterResource(id = R.drawable.icon_normal_arrow_left),
                onClick = { onClickBack() }
            )
        },
        title = {
            WantedSearchField(
                text = text,
                placeholder = placeholder,
                size = size,
                enabled = enabled,
                maxWordCount = maxWordCount,
                enabledOverflowText = enabledOverflowText,
                interactionSource = localInteractionSource,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                focused = localFocused,
                cursorBrush = cursorBrush,
                textStyle = textStyle,
                focusRequester = localFocusRequester,
                onValueChange = onValueChange
            )
        },
        actions = actions
    )
}
