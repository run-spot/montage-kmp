package com.wanted.android.wanted.design.navigations.topbar

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
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarContract.Variant
import com.wanted.android.wanted.design.navigations.topbar.view.WantedDisplayTopAppBarLayout
import com.wanted.android.wanted.design.navigations.topbar.view.WantedTopAppBarLayout
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_88

@Composable
fun WantedTopAppBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    variant: Variant = Variant.Normal,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    titleAlignCenter: Boolean = false,
    scrollableState: ScrollableState? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    if (titleAlignCenter) {
        WantedCenterTopAppBar(
            modifier = modifier,
            windowInsets = windowInsets,
            backgroundColor = backgroundColor,
            background = background,
            variant = variant,
            scrollableState = scrollableState,
            navigationIcon = navigationIcon,
            title = title,
            actions = actions
        )
        return
    }

    val isScrollBackground = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = scrollableState?.canScrollBackward) {
        isScrollBackground.value = scrollableState?.canScrollBackward == true
    }

    Box(
        modifier = when {
            variant == Variant.Floating && isScrollBackground.value
                || variant == Variant.Floating && background -> {
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
        CompositionLocalProvider(LocalWantedTopBarIconVariant.provides(variant)) {
            when (variant) {
                Variant.Normal,
                Variant.Floating,
                Variant.Search -> {
                    WantedTopAppBarLayout(
                        modifier = Modifier.windowInsetsPadding(windowInsets),
                        navigationIcon = navigationIcon,
                        title = title,
                        actions = actions
                    )
                }

                Variant.Display -> {
                    WantedDisplayTopAppBarLayout(
                        modifier = Modifier.windowInsetsPadding(windowInsets),
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
fun WantedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    variant: Variant = Variant.Normal,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    titleAlignCenter: Boolean = false,
    scrollableState: ScrollableState? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    WantedTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        variant = variant,
        backgroundColor = backgroundColor,
        background = background,
        titleAlignCenter = titleAlignCenter,
        scrollableState = scrollableState,
        navigationIcon = navigationIcon,
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = actions
    )
}
