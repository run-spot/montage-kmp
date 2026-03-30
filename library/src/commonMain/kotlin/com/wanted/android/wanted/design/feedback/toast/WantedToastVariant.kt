package com.wanted.android.wanted.design.feedback.toast

import androidx.compose.ui.graphics.Color
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_filler_svg
import com.wanted.android.wanted.design.resources.icon_normal_circle_close_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_filler_svg
import com.wanted.android.wanted.design.resources.icon_normal_triangle_exclamation_fill
import com.wanted.android.wanted.design.resources.icon_normal_triangle_exclamation_filler_svg
import com.wanted.android.wanted.design.theme.AppWantedColorScheme
import org.jetbrains.compose.resources.DrawableResource

sealed class WantedToastVariant(
    val resource: DrawableResource? = null,
    val tintColor: Color? = null,
    val backgroundResource: DrawableResource? = null,
    val backgroundTintColor: Color = AppWantedColorScheme.staticWhite
) {
    data object Message : WantedToastVariant()

    data object Positive : WantedToastVariant(
        resource = Res.drawable.icon_normal_circle_check_fill,
        tintColor = AppWantedColorScheme.statusPositive,
        backgroundResource = Res.drawable.icon_normal_circle_check_filler_svg
    )

    data object Cautionary : WantedToastVariant(
        resource = Res.drawable.icon_normal_triangle_exclamation_fill,
        tintColor = AppWantedColorScheme.statusCautionary,
        backgroundResource = Res.drawable.icon_normal_triangle_exclamation_filler_svg
    )

    data object Negative : WantedToastVariant(
        resource = Res.drawable.icon_normal_circle_close_fill,
        tintColor = AppWantedColorScheme.statusNegative,
        backgroundResource = Res.drawable.icon_normal_circle_exclamation_filler_svg
    )
}
