package com.wanted.android.wanted.design.theme
import kotlin.jvm.JvmInline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class WantedColorOpacityScheme(
    val staticWhiteOpacity5: Color = Color.Transparent,
    val staticWhiteOpacity8: Color = Color.Transparent,
    val staticWhiteOpacity12: Color = Color.Transparent,
    val staticWhiteOpacity22: Color = Color.Transparent,
    val staticWhiteOpacity28: Color = Color.Transparent,
    val staticWhiteOpacity35: Color = Color.Transparent,
    val staticWhiteOpacity52: Color = Color.Transparent,
    val staticWhiteOpacity61: Color = Color.Transparent,
    val staticWhiteOpacity74: Color = Color.Transparent,
    val staticWhiteOpacity88: Color = Color.Transparent,

    val staticBlackOpacity0: Color = Color.Transparent,
    val staticBlackOpacity8: Color = Color.Transparent,
    val staticBlackOpacity12: Color = Color.Transparent,
    val staticBlackOpacity22: Color = Color.Transparent,
    val staticBlackOpacity28: Color = Color.Transparent,
    val staticBlackOpacity35: Color = Color.Transparent,
    val staticBlackOpacity43: Color = Color.Transparent,
    val staticBlackOpacity52: Color = Color.Transparent,
    val staticBlackOpacity74: Color = Color.Transparent,
    val staticBlackOpacity88: Color = Color.Transparent,

    val primaryNormalOpacity5: Color = Color.Transparent,
    val primaryNormalOpacity8: Color = Color.Transparent,
    val primaryNormalOpacity12: Color = Color.Transparent,
    val primaryNormalOpacity22: Color = Color.Transparent,
    val primaryNormalOpacity28: Color = Color.Transparent,
    val primaryNormalOpacity35: Color = Color.Transparent,
    val primaryNormalOpacity52: Color = Color.Transparent,
    val primaryNormalOpacity61: Color = Color.Transparent,
    val primaryNormalOpacity74: Color = Color.Transparent,
    val primaryNormalOpacity88: Color = Color.Transparent,

    val labelNormalOpacity5: Color = Color.Transparent,
    val labelNormalOpacity8: Color = Color.Transparent,
    val labelNormalOpacity12: Color = Color.Transparent,

    val labelStrongOpacity5: Color = Color.Transparent,
    val labelStrongOpacity8: Color = Color.Transparent,
    val labelStrongOpacity12: Color = Color.Transparent,
    val labelStrongOpacity35: Color = Color.Transparent,
    val labelStrongOpacity52: Color = Color.Transparent,
    val labelStrongOpacity74: Color = Color.Transparent,
    val labelStrongOpacity88: Color = Color.Transparent,

    val labelAlternativeOpacity5: Color = Color.Transparent,
    val labelAlternativeOpacity8: Color = Color.Transparent,
    val labelAlternativeOpacity12: Color = Color.Transparent,
    val labelAlternativeOpacity35: Color = Color.Transparent,
    val labelAlternativeOpacity52: Color = Color.Transparent,
    val labelAlternativeOpacity74: Color = Color.Transparent,
    val labelAlternativeOpacity88: Color = Color.Transparent,


    val backgroundNormalNormalOpacity0: Color = Color.Transparent,
    val backgroundNormalNormalOpacity61: Color = Color.Transparent,

    val backgroundElevatedNormalOpacity0: Color = Color.Transparent,
    val backgroundElevatedNormalOpacity12: Color = Color.Transparent,
    val backgroundElevatedNormalOpacity88: Color = Color.Transparent,
    val backgroundElevatedNormalOpacity97: Color = Color.Transparent,

    val lineNormalOpacity28: Color = Color.Transparent,
    val lineNormalOpacity61: Color = Color.Transparent,

    val lineAlternativeOpacity52: Color = Color.Transparent,

    val statusPositiveOpacity5: Color = Color.Transparent,
    val statusPositiveOpacity8: Color = Color.Transparent,
    val statusPositiveOpacity12: Color = Color.Transparent,
    val statusPositiveOpacity16: Color = Color.Transparent,
    val statusPositiveOpacity43: Color = Color.Transparent,

    val statusNegativeOpacity8: Color = Color.Transparent,

    val accentCyanOpacity8: Color = Color.Transparent,
    val accentCyanOpacity35: Color = Color.Transparent,

    val accentLightBlueOpacity5: Color = Color.Transparent,
    val accentLightBlueOpacity8: Color = Color.Transparent,
    val accentLightBlueOpacity12: Color = Color.Transparent,

    val accentVioletOpacity5: Color = Color.Transparent,
    val accentVioletOpacity8: Color = Color.Transparent,
    val accentVioletOpacity12: Color = Color.Transparent,

    val accentPinkOpacity8: Color = Color.Transparent,
    val accentLimeOpacity8: Color = Color.Transparent
)

val AppWantedColorOpacityScheme = WantedColorOpacityScheme()


internal val LocalWantedColorOpacityScheme = WantedColorOpacitySchemeLocal()

@JvmInline
value class WantedColorOpacitySchemeLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<WantedColorOpacityScheme> = staticCompositionLocalOf { WantedColorOpacityScheme() }
) {
    val current: WantedColorOpacityScheme
        @Composable get() = delegate.current

    infix fun provides(value: WantedColorOpacityScheme) = delegate provides value
}
