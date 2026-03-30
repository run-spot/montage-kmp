package com.wanted.android.wanted.design.base

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowSpreadStyle
import com.wanted.android.wanted.design.base.WantedDropShadowDefaults.WantedShadowStyle

@Composable
actual fun Modifier.wantedDropShadow(style: WantedShadowStyle): Modifier {
    return background(
        color = style.backgroundColor,
        shape = RoundedCornerShape(style.borderRadius)
    )
}

@Composable
actual fun Modifier.wantedDropShadowSpread(style: WantedShadowSpreadStyle): Modifier {
    return background(
        color = style.backgroundColor,
        shape = RoundedCornerShape(style.borderRadius)
    )
}
