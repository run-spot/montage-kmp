package com.wanted.android.wanted.design.beta.logo

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.wanted_symbol_monochrome
import com.wanted.android.wanted.design.resources.wanted_symbol_normal
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedCircleSymbol(
    modifier: Modifier = Modifier,
    tintColor: Color? = null
) {
    Image(
        modifier = modifier,
        painter = when (tintColor) {
            null -> painterResource(Res.drawable.wanted_symbol_normal)
            else -> painterResource(Res.drawable.wanted_symbol_monochrome)
        },
        contentDescription = "WantedCircleSymbol",
        colorFilter = tintColor?.let { ColorFilter.tint(it) },
    )
}
