package com.wanted.android.wanted.design.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon

@Composable
internal fun WantedToastIcon(
    resource: DrawableResource,
    tint: Color,
    modifier: Modifier = Modifier,
    backgroundResource: DrawableResource? = null,
    backgroundColor: Color = DesignSystemTheme.colors.staticWhite,
    size: Dp = 22.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        backgroundResource?.let {
            Icon(
                contentDescription = "icon",
                painter = painterResource(backgroundResource),
                modifier = Modifier.size(12.dp),
                tint = backgroundColor
            )
        } ?: Box(
            modifier = Modifier
                .size(12.dp)
                .background(backgroundColor)
        )

        Icon(
            contentDescription = "icon",
            painter = painterResource(resource),
            modifier = Modifier.size(22.dp),
            tint = tint
        )
    }
}
