package com.wanted.android.wanted.design.theme
import kotlin.jvm.JvmInline

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class WantedColorScheme(
    val staticWhite: Color = Color.Transparent,
    val staticBlack: Color = Color.Transparent,

    val primaryNormal: Color = Color.Transparent,
    val primaryStrong: Color = Color.Transparent,
    val primaryHeavy: Color = Color.Transparent,

    val labelNormal: Color = Color.Transparent,
    val labelStrong: Color = Color.Transparent,
    val labelNeutral: Color = Color.Transparent,
    val labelAlternative: Color = Color.Transparent,
    val labelAssistive: Color = Color.Transparent,
    val labelDisable: Color = Color.Transparent,

    val backgroundNormalNormal: Color = Color.Transparent,
    val backgroundNormalAlternative: Color = Color.Transparent,
    val backgroundElevatedNormal: Color = Color.Transparent,
    val backgroundElevatedAlternative: Color = Color.Transparent,
    val backgroundTransparentNormal: Color = Color.Transparent,
    val backgroundTransparentAlternative: Color = Color.Transparent,

    val interactionInactive: Color = Color.Transparent,
    val interactionDisable: Color = Color.Transparent,

    val lineNormalNormal: Color = Color.Transparent,
    val lineNormalNeutral: Color = Color.Transparent,
    val lineNormalAlternative: Color = Color.Transparent,
    val lineSolidNormal: Color = Color.Transparent,
    val lineSolidNeutral: Color = Color.Transparent,
    val lineSolidAlternative: Color = Color.Transparent,

    val statusPositive: Color = Color.Transparent,
    val statusNegative: Color = Color.Transparent,
    val statusCautionary: Color = Color.Transparent,

    val accentBackgroundLime: Color = Color.Transparent,
    val accentBackgroundCyan: Color = Color.Transparent,
    val accentBackgroundLightBlue: Color = Color.Transparent,
    val accentBackgroundViolet: Color = Color.Transparent,
    val accentBackgroundPurple: Color = Color.Transparent,
    val accentBackgroundPink: Color = Color.Transparent,
    val accentBackgroundRedOrange: Color = Color.Transparent,

    val accentForegroundRed: Color = Color.Transparent,
    val accentForegroundRedOrange: Color = Color.Transparent,
    val accentForegroundOrange: Color = Color.Transparent,
    val accentForegroundLime: Color = Color.Transparent,
    val accentForegroundGreen: Color = Color.Transparent,
    val accentForegroundCyan: Color = Color.Transparent,
    val accentForegroundLightBlue: Color = Color.Transparent,
    val accentForegroundBlue: Color = Color.Transparent,
    val accentForegroundViolet: Color = Color.Transparent,
    val accentForegroundPurple: Color = Color.Transparent,
    val accentForegroundPink: Color = Color.Transparent,

    val inversePrimary: Color = Color.Transparent,
    val inverseBackground: Color = Color.Transparent,
    val inverseLabel: Color = Color.Transparent,

    val fillNormal: Color = Color.Transparent,
    val fillStrong: Color = Color.Transparent,
    val fillAlternative: Color = Color.Transparent,

    val materialDimmer: Color = Color.Transparent,

    val transparent: Color = Color.Transparent
)

private fun color(value: Long) = Color(value)

val LightWantedColorScheme = WantedColorScheme(
    staticWhite = color(0xFFFFFFFF),
    staticBlack = color(0xFF000000),

    primaryNormal = color(0xFF0066FF),
    primaryStrong = color(0xFF005EEB),
    primaryHeavy = color(0xFF0054D1),

    labelNormal = color(0xFF171719),
    labelStrong = color(0xFF000000),
    labelNeutral = color(0xFF2E2F33),
    labelAlternative = color(0xFF37383C),
    labelAssistive = color(0xFF37383C),
    labelDisable = color(0xFF37383C),

    backgroundNormalNormal = color(0xFFFFFFFF),
    backgroundNormalAlternative = color(0xFFF7F7F8),
    backgroundElevatedNormal = color(0xFFFFFFFF),
    backgroundElevatedAlternative = color(0xFFF7F7F8),
    backgroundTransparentNormal = color(0xFFFFFFFF),
    backgroundTransparentAlternative = color(0xFFFFFFFF),

    interactionInactive = color(0xFF989BA2),
    interactionDisable = color(0xFFF4F4F5),

    lineNormalNormal = color(0xFF70737C),
    lineNormalNeutral = color(0xFF70737C),
    lineNormalAlternative = color(0xFF70737C),
    lineSolidNormal = color(0xFFE1E2E4),
    lineSolidNeutral = color(0xFFEAEBEC),
    lineSolidAlternative = color(0xFFF4F4F5),

    statusPositive = color(0xFF00BF40),
    statusNegative = color(0xFFFF4242),
    statusCautionary = color(0xFFFF9200),

    accentBackgroundLime = color(0xFF58CF04),
    accentBackgroundCyan = color(0xFF00BDDE),
    accentBackgroundLightBlue = color(0xFF00AEFF),
    accentBackgroundViolet = color(0xFF6541F2),
    accentBackgroundPurple = color(0xFFCB59FF),
    accentBackgroundPink = color(0xFFF553DA),
    accentBackgroundRedOrange = color(0xFFFF5E00),

    accentForegroundRed = color(0xFFE52222),
    accentForegroundRedOrange = color(0xFFF55A00),
    accentForegroundOrange = color(0xFFD17600),
    accentForegroundLime = color(0xFF429E00),
    accentForegroundGreen = color(0xFF009632),
    accentForegroundCyan = color(0xFF0098B2),
    accentForegroundLightBlue = color(0xFF008DCF),
    accentForegroundBlue = color(0xFF005EEB),
    accentForegroundViolet = color(0xFF5B37ED),
    accentForegroundPurple = color(0xFFAD36E3),
    accentForegroundPink = color(0xFFE846CD),

    inversePrimary = color(0xFF3385FF),
    inverseBackground = color(0xFF1B1C1E),
    inverseLabel = color(0xFFF7F7F8),

    fillNormal = color(0xFF70737C),
    fillStrong = color(0xFF70737C),
    fillAlternative = color(0xFF70737C),

    materialDimmer = color(0xFF171719),
)

val DarkWantedColorScheme = WantedColorScheme(
    staticWhite = color(0xFFFFFFFF),
    staticBlack = color(0xFF000000),

    primaryNormal = color(0xFF0066FF),
    primaryStrong = color(0xFF005EEB),
    primaryHeavy = color(0xFF0054D1),

    labelNormal = color(0xFF171719),
    labelStrong = color(0xFF000000),
    labelNeutral = color(0xE02E2F33),
    labelAlternative = color(0xFF37383C),
    labelAssistive = color(0x4737383C),
    labelDisable = color(0x2937383C),

    backgroundNormalNormal = color(0xFFFFFFFF),
    backgroundNormalAlternative = color(0xFFF7F7F8),
    backgroundElevatedNormal = color(0xFFFFFFFF),
    backgroundElevatedAlternative = color(0xFFF7F7F8),
    backgroundTransparentNormal = color(0xFFFFFFFF),
    backgroundTransparentAlternative = color(0xFFFFFFFF),

    interactionInactive = color(0xFF989BA2),
    interactionDisable = color(0xFFF4F4F5),

    lineNormalNormal = color(0xFF70737C),
    lineNormalNeutral = color(0xFF70737C),
    lineNormalAlternative = color(0x1470737C),
    lineSolidNormal = color(0xFF37383C),
    lineSolidNeutral = color(0xFF333438),
    lineSolidAlternative = color(0xFF2E2F33),

    statusPositive = color(0xFF00BF40),
    statusNegative = color(0xFFFF4242),
    statusCautionary = color(0xFFFF9200),

    accentBackgroundLime = color(0xFF58CF04),
    accentBackgroundCyan = color(0xFF00BDDE),
    accentBackgroundLightBlue = color(0xFF00AEFF),
    accentBackgroundViolet = color(0xFF6541F2),
    accentBackgroundPurple = color(0xFFCB59FF),
    accentBackgroundPink = color(0xFFF553DA),
    accentBackgroundRedOrange = color(0xFFFF5E00),

    accentForegroundRed = color(0xFFE52222),
    accentForegroundRedOrange = color(0xFFF55A00),
    accentForegroundOrange = color(0xFFD17600),
    accentForegroundLime = color(0xFF429E00),
    accentForegroundGreen = color(0xFF009632),
    accentForegroundCyan = color(0xFF0098B2),
    accentForegroundLightBlue = color(0xFF008DCF),
    accentForegroundBlue = color(0xFF005EEB),
    accentForegroundViolet = color(0xFF5B37ED),
    accentForegroundPurple = color(0xFFAD36E3),
    accentForegroundPink = color(0xFFE846CD),

    inversePrimary = color(0xFF3385FF),
    inverseBackground = color(0xFF1B1C1E),
    inverseLabel = color(0xFFF7F7F8),

    fillNormal = color(0x1470737C),
    fillStrong = color(0x2970737C),
    fillAlternative = color(0x0D70737C),

    materialDimmer = color(0x85171719),
)

// Backward-compatible alias for call sites that still expect a single default palette.
val AppWantedColorScheme = LightWantedColorScheme

internal val LocalWantedColorScheme = WantedColorSchemeLocal()

@JvmInline
value class WantedColorSchemeLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<WantedColorScheme> = staticCompositionLocalOf { WantedColorScheme() }
) {
    val current: WantedColorScheme
        @Composable get() = delegate.current

    infix fun provides(value: WantedColorScheme) = delegate provides value


    fun getSystemColor(
        colorScheme: WantedColorScheme,
        isDarkTheme: Boolean
    ) = if (isDarkTheme) {
        darkColorScheme(
            primary = colorScheme.primaryNormal,
            secondary = colorScheme.primaryNormal,
            background = colorScheme.backgroundNormalNormal,
            surface = colorScheme.backgroundNormalNormal,
            error = colorScheme.backgroundNormalNormal,
            onPrimary = colorScheme.labelNormal,
            onSecondary = colorScheme.labelNormal,
            onBackground = colorScheme.labelNormal,
            onSurface = colorScheme.labelNormal,
            onError = colorScheme.statusNegative,
        )
    } else {
        lightColorScheme(
            primary = colorScheme.primaryNormal,
            secondary = colorScheme.primaryNormal,
            background = colorScheme.backgroundNormalNormal,
            surface = colorScheme.backgroundNormalNormal,
            error = colorScheme.backgroundNormalNormal,
            onPrimary = colorScheme.labelNormal,
            onSecondary = colorScheme.labelNormal,
            onBackground = colorScheme.labelNormal,
            onSurface = colorScheme.labelNormal,
            onError = colorScheme.statusNegative
        )
    }
}
