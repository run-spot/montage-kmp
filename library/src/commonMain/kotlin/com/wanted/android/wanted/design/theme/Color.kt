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
val LightWantedColorScheme = WantedColorScheme(
    staticWhite = WantedAtomicColors.common100,
    staticBlack = WantedAtomicColors.common0,

    primaryNormal = WantedAtomicColors.blue50,
    primaryStrong = WantedAtomicColors.blue45,
    primaryHeavy = WantedAtomicColors.blue40,

    labelNormal = WantedAtomicColors.coolNeutral10,
    labelStrong = WantedAtomicColors.common0,
    labelNeutral = WantedAtomicColors.coolNeutral22,
    labelAlternative = WantedAtomicColors.coolNeutral25,
    labelAssistive = WantedAtomicColors.coolNeutral25,
    labelDisable = WantedAtomicColors.coolNeutral25,

    backgroundNormalNormal = WantedAtomicColors.common100,
    backgroundNormalAlternative = WantedAtomicColors.coolNeutral99,
    backgroundElevatedNormal = WantedAtomicColors.common100,
    backgroundElevatedAlternative = WantedAtomicColors.coolNeutral99,
    backgroundTransparentNormal = WantedAtomicColors.common100,
    backgroundTransparentAlternative = WantedAtomicColors.common100,

    interactionInactive = WantedAtomicColors.coolNeutral70,
    interactionDisable = WantedAtomicColors.coolNeutral98,

    lineNormalNormal = WantedAtomicColors.coolNeutral50,
    lineNormalNeutral = WantedAtomicColors.coolNeutral50,
    lineNormalAlternative = WantedAtomicColors.coolNeutral50,
    lineSolidNormal = WantedAtomicColors.coolNeutral96,
    lineSolidNeutral = WantedAtomicColors.coolNeutral97,
    lineSolidAlternative = WantedAtomicColors.coolNeutral98,

    statusPositive = WantedAtomicColors.green50,
    statusNegative = WantedAtomicColors.red50,
    statusCautionary = WantedAtomicColors.orange50,

    accentBackgroundLime = WantedAtomicColors.lime50,
    accentBackgroundCyan = WantedAtomicColors.cyan50,
    accentBackgroundLightBlue = WantedAtomicColors.lightblue50,
    accentBackgroundViolet = WantedAtomicColors.violet50,
    accentBackgroundPurple = WantedAtomicColors.purple50,
    accentBackgroundPink = WantedAtomicColors.pink50,
    accentBackgroundRedOrange = WantedAtomicColors.redorange50,

    accentForegroundRed = WantedAtomicColors.red40,
    accentForegroundRedOrange = WantedAtomicColors.redorange48,
    accentForegroundOrange = WantedAtomicColors.orange39,
    accentForegroundLime = WantedAtomicColors.lime37,
    accentForegroundGreen = WantedAtomicColors.green40,
    accentForegroundCyan = WantedAtomicColors.cyan40,
    accentForegroundLightBlue = WantedAtomicColors.lightblue40,
    accentForegroundBlue = WantedAtomicColors.blue45,
    accentForegroundViolet = WantedAtomicColors.violet45,
    accentForegroundPurple = WantedAtomicColors.purple40,
    accentForegroundPink = WantedAtomicColors.pink46,

    inversePrimary = WantedAtomicColors.blue60,
    inverseBackground = WantedAtomicColors.coolNeutral15,
    inverseLabel = WantedAtomicColors.coolNeutral99,

    fillNormal = WantedAtomicColors.coolNeutral50,
    fillStrong = WantedAtomicColors.coolNeutral50,
    fillAlternative = WantedAtomicColors.coolNeutral50,

    materialDimmer = WantedAtomicColors.coolNeutral10,
)

val DarkWantedColorScheme = WantedColorScheme(
    staticWhite = WantedAtomicColors.common100,
    staticBlack = WantedAtomicColors.common0,

    primaryNormal = WantedAtomicColors.blue60,
    primaryStrong = WantedAtomicColors.blue55,
    primaryHeavy = WantedAtomicColors.blue50,

    labelNormal = WantedAtomicColors.coolNeutral99,
    labelStrong = WantedAtomicColors.common100,
    labelNeutral = WantedAtomicColors.coolNeutral90Opacity88,
    labelAlternative = WantedAtomicColors.coolNeutral80Opacity61,
    labelAssistive = WantedAtomicColors.coolNeutral80Opacity28,
    labelDisable = WantedAtomicColors.coolNeutral70Opacity16,

    backgroundNormalNormal = WantedAtomicColors.coolNeutral15,
    backgroundNormalAlternative = WantedAtomicColors.coolNeutral5,
    backgroundElevatedNormal = WantedAtomicColors.coolNeutral17,
    backgroundElevatedAlternative = WantedAtomicColors.coolNeutral7,
    backgroundTransparentNormal = WantedAtomicColors.coolNeutral17Opacity61,
    backgroundTransparentAlternative = WantedAtomicColors.coolNeutral17Opacity61,

    interactionInactive = WantedAtomicColors.coolNeutral40,
    interactionDisable = WantedAtomicColors.coolNeutral22,

    lineNormalNormal = WantedAtomicColors.coolNeutral50Opacity32,
    lineNormalNeutral = WantedAtomicColors.coolNeutral50Opacity28,
    lineNormalAlternative = WantedAtomicColors.coolNeutral50Opacity22,
    lineSolidNormal = WantedAtomicColors.coolNeutral25,
    lineSolidNeutral = WantedAtomicColors.coolNeutral23,
    lineSolidAlternative = WantedAtomicColors.coolNeutral22,

    statusPositive = WantedAtomicColors.green60,
    statusNegative = WantedAtomicColors.red60,
    statusCautionary = WantedAtomicColors.orange60,

    accentBackgroundLime = WantedAtomicColors.lime60,
    accentBackgroundCyan = WantedAtomicColors.cyan60,
    accentBackgroundLightBlue = WantedAtomicColors.lightblue60,
    accentBackgroundViolet = WantedAtomicColors.violet60,
    accentBackgroundPurple = WantedAtomicColors.purple60,
    accentBackgroundPink = WantedAtomicColors.pink60,
    accentBackgroundRedOrange = WantedAtomicColors.redorange60,

    accentForegroundRed = WantedAtomicColors.red60,
    accentForegroundRedOrange = WantedAtomicColors.redorange60,
    accentForegroundOrange = WantedAtomicColors.orange50,
    accentForegroundLime = WantedAtomicColors.lime50,
    accentForegroundGreen = WantedAtomicColors.green60,
    accentForegroundCyan = WantedAtomicColors.cyan50,
    accentForegroundLightBlue = WantedAtomicColors.lightblue50,
    accentForegroundBlue = WantedAtomicColors.blue65,
    accentForegroundViolet = WantedAtomicColors.violet70,
    accentForegroundPurple = WantedAtomicColors.purple60,
    accentForegroundPink = WantedAtomicColors.pink60,

    inversePrimary = WantedAtomicColors.blue50,
    inverseBackground = WantedAtomicColors.common100,
    inverseLabel = WantedAtomicColors.coolNeutral10,

    fillNormal = WantedAtomicColors.coolNeutral50Opacity22,
    fillStrong = WantedAtomicColors.coolNeutral50Opacity28,
    fillAlternative = WantedAtomicColors.coolNeutral50Opacity12,

    materialDimmer = WantedAtomicColors.coolNeutral10Opacity74,
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
