package com.wanted.android.wanted.design.navigations.topbar.dialogtopbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarIconButton
import com.wanted.android.wanted.design.navigations.topbar.LocalWantedTopBarIconVariant
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarContract
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarDefaults
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_88

@Composable
fun WantedDialogTopAppBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    variant: WantedDialogTopAppBarContract.Variant = WantedDialogTopAppBarContract.Variant.Normal,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    scrollableState: ScrollableState? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    val isScrollBackground = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = scrollableState?.canScrollBackward) {
        isScrollBackground.value = scrollableState?.canScrollBackward == true
    }

    Box(
        modifier = when {
            variant == WantedDialogTopAppBarContract.Variant.Floating && isScrollBackground.value
                || variant == WantedDialogTopAppBarContract.Variant.Floating && background -> {
                modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                backgroundColor.copy(alpha = OPACITY_88),
                                DesignSystemTheme.colors.transparent
                            )
                        )
                    )
                    .padding(bottom = 16.dp)
            }

            !background && !isScrollBackground.value -> modifier.background(DesignSystemTheme.colors.transparent)
            else -> modifier.background(backgroundColor)
        }
    ) {
        CompositionLocalProvider(
            LocalWantedTopBarIconVariant.provides(
                when (variant) {
                    WantedDialogTopAppBarContract.Variant.Normal -> WantedTopAppBarContract.Variant.Normal
                    WantedDialogTopAppBarContract.Variant.Emphasized -> WantedTopAppBarContract.Variant.Normal
                    WantedDialogTopAppBarContract.Variant.Display -> WantedTopAppBarContract.Variant.Display
                    WantedDialogTopAppBarContract.Variant.Floating -> WantedTopAppBarContract.Variant.Floating
                }
            )
        ) {
            when (variant) {
                WantedDialogTopAppBarContract.Variant.Normal,
                WantedDialogTopAppBarContract.Variant.Floating -> {
                    WantedDialogCenterTopAppBarLayout(
                        modifier = Modifier.windowInsetsPadding(windowInsets),
                        navigationIcon = navigationIcon,
                        title = title,
                        actions = actions
                    )
                }

                WantedDialogTopAppBarContract.Variant.Emphasized,
                WantedDialogTopAppBarContract.Variant.Display -> {
                    WantedDialogTopAppBarLayout(
                        modifier = Modifier.windowInsetsPadding(windowInsets),
                        variant = variant,
                        navigationIcon = navigationIcon,
                        title = title,
                        actions = actions
                    )
                }
            }
        }
    }
}

@Composable
fun WantedDialogTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    variant: WantedDialogTopAppBarContract.Variant = WantedDialogTopAppBarContract.Variant.Normal,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    scrollableState: ScrollableState? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    WantedDialogTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        variant = variant,
        backgroundColor = backgroundColor,
        background = background,
        scrollableState = scrollableState,
        navigationIcon = navigationIcon,
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        actions = actions
    )
}
