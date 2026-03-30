package com.wanted.android.wanted.design.actions.button.iconbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.feedback.pushbadge.WantedPushBadge
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_company
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedIconButtonNormal(
    icon: DrawableResource,
    modifier: Modifier,
    enabled: Boolean = true,
    tint: Color = DesignSystemTheme.colors.labelNormal,
    pushBadge: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    WantedTouchArea(
        content = {
            Icon(
                modifier = modifier,
                painter = painterResource(icon),
                contentDescription = "",
                tint = if (enabled) tint else DesignSystemTheme.colors.labelDisable,
            )

            pushBadge?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(10.dp, (-10).dp)
                ) {
                    pushBadge()
                }
            }
        },
        enabled = enabled,
        shape = CircleShape,
        horizontalPadding = 8.dp,
        verticalPadding = 8.dp,
        onClick = onClick
    )
}

@DevicePreviews
@Composable
private fun WantedIconButtonNormalPreview() {
    DesignSystemTheme {
        WantedIconButtonNormal(
            modifier = Modifier,
            icon = Res.drawable.icon_normal_company,
            pushBadge = { WantedPushBadge() },
            onClick = {}
        )
    }
}
