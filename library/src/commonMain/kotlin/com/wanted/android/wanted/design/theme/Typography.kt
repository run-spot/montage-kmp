package com.wanted.android.wanted.design.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.pretendard_bold
import com.wanted.android.wanted.design.resources.pretendard_medium
import com.wanted.android.wanted.design.resources.pretendard_regular
import com.wanted.android.wanted.design.resources.pretendard_semi_bold
import org.jetbrains.compose.resources.Font

@Immutable
data class WantedTypography(
    private val defaultFontFamily: FontFamily = FontFamily.Default,
    private val boldFontFamily: FontFamily = FontFamily.Default,
    val display1Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 56.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.0319).em,
        lineHeight = 72.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display1Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 56.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.0319).em,
        lineHeight = 72.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display1Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 56.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.0319).em,
        lineHeight = 72.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display2Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.0282).em,
        lineHeight = 52.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display2Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.0282).em,
        lineHeight = 52.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display2Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.0282).em,
        lineHeight = 52.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display3Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.027).em,
        lineHeight = 48.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display3Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.027).em,
        lineHeight = 48.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val display3Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.027).em,
        lineHeight = 48.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title1Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.0253).em,
        lineHeight = 44.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title1Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.0253).em,
        lineHeight = 44.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title1Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.0253).em,
        lineHeight = 44.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title2Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.0236).em,
        lineHeight = 38.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title2Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.0236).em,
        lineHeight = 38.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title2Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.0236).em,
        lineHeight = 38.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title3Regular: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.023).em,
        lineHeight = 32.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title3Medium: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.023).em,
        lineHeight = 32.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val title3Bold: TextStyle = TextStyle(
        fontFamily = boldFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.023).em,
        lineHeight = 32.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading1Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.0194).em,
        lineHeight = 30.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading1Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.0194).em,
        lineHeight = 30.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading1Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = (-0.0194).em,
        lineHeight = 30.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading2Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.012).em,
        lineHeight = 28.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading2Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.012).em,
        lineHeight = 28.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val heading2Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = (-0.012).em,
        lineHeight = 28.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline1Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.002).em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline1Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (-0.002).em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline1Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = (-0.002).em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline2Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline2Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headline2Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0057.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0057.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0057.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1ReadingRegular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0057.em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1ReadingMedium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0057.em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body1ReadingBold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0057.em,
        lineHeight = 26.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0096.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0096.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0096.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2ReadingRegular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0096.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2ReadingMedium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0096.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val body2ReadingBold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0096.em,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0145.em,
        lineHeight = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0145.em,
        lineHeight = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0145.em,
        lineHeight = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1ReadingRegular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0145.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1ReadingMedium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0145.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label1ReadingBold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0145.em,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label2Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label2Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val label2Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0194.em,
        lineHeight = 18.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption1Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0252.em,
        lineHeight = 16.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption1Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0252.em,
        lineHeight = 16.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption1Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0252.em,
        lineHeight = 16.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption2Regular: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.0311.em,
        lineHeight = 14.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption2Medium: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 0.0311.em,
        lineHeight = 14.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val caption2Bold: TextStyle = TextStyle(
        fontFamily = defaultFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.0311.em,
        lineHeight = 14.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
)

@Composable
internal fun rememberPretendardFontFamily(): FontFamily {
    val regular = Font(Res.font.pretendard_regular, weight = FontWeight.Normal)
    val medium = Font(Res.font.pretendard_medium, weight = FontWeight.Medium)
    val semiBold = Font(Res.font.pretendard_semi_bold, weight = FontWeight.SemiBold)
    val bold = Font(Res.font.pretendard_bold, weight = FontWeight.Bold)

    return remember(regular, medium, semiBold, bold) {
    FontFamily(
            regular,
            medium,
            semiBold,
            bold
        )
    }
}

@Composable
internal fun rememberPretendardBoldFontFamily(): FontFamily {
    val regularDisplay = Font(Res.font.pretendard_semi_bold, weight = FontWeight.W400)
    val mediumDisplay = Font(Res.font.pretendard_semi_bold, weight = FontWeight.W500)
    val boldDisplay = Font(Res.font.pretendard_bold, weight = FontWeight.W700)

    return remember(regularDisplay, mediumDisplay, boldDisplay) {
        FontFamily(
            regularDisplay,
            mediumDisplay,
            boldDisplay
        )
    }
}

@Composable
internal fun rememberPretendardTypography(defaultFontFamily: FontFamily): Typography = remember(defaultFontFamily) {
    Typography().let {
        it.copy(
            displayLarge = it.displayLarge.copy(fontFamily = defaultFontFamily),
            displayMedium = it.displayMedium.copy(fontFamily = defaultFontFamily),
            displaySmall = it.displaySmall.copy(fontFamily = defaultFontFamily),
            headlineLarge = it.headlineLarge.copy(fontFamily = defaultFontFamily),
            headlineMedium = it.headlineMedium.copy(fontFamily = defaultFontFamily),
            headlineSmall = it.headlineSmall.copy(fontFamily = defaultFontFamily),
            titleLarge = it.titleLarge.copy(fontFamily = defaultFontFamily),
            titleMedium = it.titleMedium.copy(fontFamily = defaultFontFamily),
            titleSmall = it.titleSmall.copy(fontFamily = defaultFontFamily),
            bodyLarge = it.bodyLarge.copy(fontFamily = defaultFontFamily),
            bodyMedium = it.bodyMedium.copy(fontFamily = defaultFontFamily),
            bodySmall = it.bodySmall.copy(fontFamily = defaultFontFamily),
            labelLarge = it.labelLarge.copy(fontFamily = defaultFontFamily),
            labelMedium = it.labelMedium.copy(fontFamily = defaultFontFamily),
            labelSmall = it.labelSmall.copy(fontFamily = defaultFontFamily),
        )
    }
}


val LocalWantedTypography = staticCompositionLocalOf { WantedTypography() }
