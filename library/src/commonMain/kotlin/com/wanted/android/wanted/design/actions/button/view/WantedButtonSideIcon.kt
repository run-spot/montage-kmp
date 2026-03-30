package com.wanted.android.wanted.design.actions.button.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun WantedButtonSideIcon(
    modifier: Modifier,
    resource: DrawableResource,
    tint: Color
) {
    Image(
        modifier = modifier,
        painter = painterResource(resource),
        contentScale = ContentScale.FillHeight,
        colorFilter = ColorFilter.tint(color = tint),
        contentDescription = "WantedButton 양 옆에 들어가는 아이콘",
    )
}
