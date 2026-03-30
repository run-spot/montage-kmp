package com.wanted.android.wanted.design.actions.button.iconbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_company
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.clickOnce
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedIconButtonSolid(
    icon: DrawableResource,
    size: WantedIconButtonSize,
    modifier: Modifier,
    enabled: Boolean = true,
    tint: Color = DesignSystemTheme.colors.staticWhite,
    background: Color = DesignSystemTheme.colors.primaryNormal,
    onClick: () -> Unit = {}
) {
    WantedIconButtonSolid(
        modifier = modifier.size(size.size),
        icon = icon,
        padding = size.padding,
        enabled = enabled,
        tint = tint,
        background = background,
        onClick = onClick
    )
}

@Composable
fun WantedIconButtonSolid(
    icon: DrawableResource,
    modifier: Modifier,
    enabled: Boolean = true,
    padding: Dp = 10.dp,
    tint: Color = DesignSystemTheme.colors.staticWhite,
    background: Color = DesignSystemTheme.colors.primaryNormal,
    onClick: () -> Unit = {}
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .background(if (enabled) background else DesignSystemTheme.colors.fillNormal)
            .clickOnce(enabled) { onClick() }
            .padding(padding),
        painter = painterResource(icon),
        contentDescription = "",
        tint = if (enabled) tint else DesignSystemTheme.colors.labelDisable
    )
}

@DevicePreviews
@Composable
private fun WantedIconButtonSolidPreview() {
    DesignSystemTheme {
        WantedIconButtonSolid(
            modifier = Modifier.size(40.dp),
            size = WantedIconButtonSize.Medium,
            icon = Res.drawable.icon_normal_company,
            onClick = {}
        )
    }
}
