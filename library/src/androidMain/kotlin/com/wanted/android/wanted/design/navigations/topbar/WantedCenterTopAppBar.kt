package com.wanted.android.wanted.design.navigations.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.designsystem.R
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarContract.Variant
import com.wanted.android.wanted.design.navigations.topbar.view.WantedCenterTopAppBarLayout
import com.wanted.android.wanted.design.navigations.topbar.view.WantedDisplayTopAppBarLayout
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_88

@Composable
private fun WantedCenterTopAppBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    variant: Variant = Variant.Normal,
    scrollableState: ScrollableState? = null,
    title: String = "",
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {

    WantedCenterTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        backgroundColor = backgroundColor,
        background = background,
        variant = variant,
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

@Composable
private fun WantedCenterBackTopAppBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
    background: Boolean = true,
    variant: Variant = Variant.Normal,
    scrollableState: ScrollableState? = null,
    title: String = "",
    actions: @Composable (RowScope.() -> Unit)? = null,
    onClickBack: () -> Unit = {}
) {
    WantedCenterTopAppBar(
        modifier = modifier,
        windowInsets = windowInsets,
        backgroundColor = backgroundColor,
        background = background,
        variant = variant,
        scrollableState = scrollableState,
        navigationIcon = {
            WantedTopAppBarIconButton(
                painter = painterResource(id = R.drawable.icon_normal_arrow_left),
                onClick = { onClickBack() }
            )
        },
        title = title,
        actions = actions
    )
}

@Composable
fun WantedCenterTopAppBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    background: Boolean = true,
    variant: Variant = Variant.Normal,
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

            !background && !isScrollBackground.value -> {
                modifier.background(DesignSystemTheme.colors.transparent)
            }

            else -> {
                modifier.background(backgroundColor)
            }
        }
    ) {
        CompositionLocalProvider(LocalWantedTopBarIconVariant.provides(variant)) {
            when (variant) {
                Variant.Normal -> {
                    WantedCenterTopAppBarLayout(
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

                else -> {
                    WantedCenterTopAppBarLayout(
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


@DevicePreviews
@Composable
private fun WantedCenterTopAppBarPreview() {
    DesignSystemTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DesignSystemTheme.colors.backgroundNormalNormal),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            WantedCenterTopAppBar(
                title = "타이틀",
                actions = {
                    WantedTopAppBarIconButton(
                        painter = painterResource(id = R.drawable.icon_normal_share),
                        onClick = { }
                    )
                    WantedTopAppBarIconButton(
                        painter = painterResource(id = R.drawable.icon_normal_share),
                        onClick = { }
                    )
                }
            )

            WantedCenterTopAppBar(
                navigationIcon = {
                    WantedTopAppBarIconButton(
                        painter = painterResource(id = R.drawable.icon_normal_arrow_left),
                        onClick = { }
                    )
                },
                title = "타이틀",
                actions = {
                    WantedTopAppBarIconButton(
                        painter = painterResource(id = R.drawable.icon_normal_share),
                        onClick = { }
                    )
                    WantedTopAppBarIconButton(
                        painter = painterResource(id = R.drawable.icon_normal_share),
                        onClick = { }
                    )
                }
            )
        }
    }
}