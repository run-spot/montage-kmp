package com.wanted.android.wanted.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


@Composable
fun DesignSystemTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    appTheme: WantedColorScheme = LightWantedColorScheme,
    darkAppTheme: WantedColorScheme = DarkWantedColorScheme,
    content: @Composable () -> Unit
) {
    val activeTheme = if (isDarkTheme) darkAppTheme else appTheme
    CompositionLocalProvider(
        LocalWantedTypography.provides(WantedTypography()),
        LocalWantedColorScheme provides activeTheme,
        LocalWantedColorOpacityScheme provides AppWantedColorOpacityScheme
    ) {
        MaterialTheme(
            colorScheme = LocalWantedColorScheme.getSystemColor(
                colorScheme = activeTheme,
                isDarkTheme = isDarkTheme
            ),
            typography = pretendardTypography,
            shapes = OneIdShapes,
            content = content
        )
    }
}

object DesignSystemTheme {
    val typography: WantedTypography
        @Composable
        get() = LocalWantedTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val colors: WantedColorScheme
        @Composable
        get() = LocalWantedColorScheme.current

    val colorsOpacity: WantedColorOpacityScheme
        @Composable
        get() = LocalWantedColorOpacityScheme.current
}
