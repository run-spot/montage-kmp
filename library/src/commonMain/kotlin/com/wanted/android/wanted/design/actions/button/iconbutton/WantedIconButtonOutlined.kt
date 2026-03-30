package com.wanted.android.wanted.design.actions.button.iconbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun WantedIconButtonOutlined(
    icon: DrawableResource,
    size: WantedIconButtonSize,
    modifier: Modifier,
    enabled: Boolean = true,
    outlineColor: Color = DesignSystemTheme.colors.lineNormalNeutral,
    disableOutlineColor: Color = DesignSystemTheme.colors.lineNormalNeutral,
    tint: Color = DesignSystemTheme.colors.labelNormal,
    disableTint: Color = DesignSystemTheme.colors.labelDisable,
    background: Color = DesignSystemTheme.colors.transparent,
    disableBackground: Color = DesignSystemTheme.colors.transparent,
    onClick: () -> Unit = {},
) {
    WantedIconButtonOutlined(
        modifier = modifier.size(size.size),
        icon = icon,
        enabled = enabled,
        padding = size.padding,
        outlineColor = outlineColor,
        disableOutlineColor = disableOutlineColor,
        tint = tint,
        disableTint = disableTint,
        background = background,
        disableBackground = disableBackground,
        onClick = onClick
    )
}

@Composable
fun WantedIconButtonOutlined(
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    enabled: Boolean = true,
    outlineColor: Color = DesignSystemTheme.colors.lineNormalNeutral,
    disableOutlineColor: Color = DesignSystemTheme.colors.lineNormalNeutral,
    tint: Color = DesignSystemTheme.colors.labelNormal,
    disableTint: Color = DesignSystemTheme.colors.labelDisable,
    background: Color = DesignSystemTheme.colors.transparent,
    disableBackground: Color = DesignSystemTheme.colors.transparent,
    onClick: () -> Unit = {},
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .background(if (enabled) background else disableBackground)
            .border(
                width = 1.dp,
                color = if (enabled) outlineColor else disableOutlineColor,
                shape = CircleShape
            )
            .clickOnce(enabled) { onClick() }
            .padding(padding),
        painter = painterResource(icon),
        contentDescription = "",
        tint = if (enabled) tint else disableTint
    )
}

@DevicePreviews
@Composable
private fun WantedIconButtonOutlinedPreview() {
    DesignSystemTheme {
        WantedIconButtonOutlined(
            modifier = Modifier.size(40.dp),
            size = WantedIconButtonSize.Medium,
            icon = Res.drawable.icon_normal_company,
            onClick = {}
        )
    }
}
