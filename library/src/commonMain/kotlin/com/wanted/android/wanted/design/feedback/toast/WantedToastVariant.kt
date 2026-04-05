package com.wanted.android.wanted.design.feedback.toast

import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_filler_svg
import com.wanted.android.wanted.design.resources.icon_normal_circle_close_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_filler_svg
import com.wanted.android.wanted.design.resources.icon_normal_triangle_exclamation_fill
import com.wanted.android.wanted.design.resources.icon_normal_triangle_exclamation_filler_svg
import org.jetbrains.compose.resources.DrawableResource

sealed class WantedToastVariant(
    val resource: DrawableResource? = null,
    val backgroundResource: DrawableResource? = null,
) {
    data object Message : WantedToastVariant()

    data object Positive : WantedToastVariant(
        resource = Res.drawable.icon_normal_circle_check_fill,
        backgroundResource = Res.drawable.icon_normal_circle_check_filler_svg
    )

    data object Cautionary : WantedToastVariant(
        resource = Res.drawable.icon_normal_triangle_exclamation_fill,
        backgroundResource = Res.drawable.icon_normal_triangle_exclamation_filler_svg
    )

    data object Negative : WantedToastVariant(
        resource = Res.drawable.icon_normal_circle_close_fill,
        backgroundResource = Res.drawable.icon_normal_circle_exclamation_filler_svg
    )
}
