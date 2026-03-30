package com.wanted.android.wanted.design.actions.button.iconbutton

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_company
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_22
import com.wanted.android.wanted.design.util.OPACITY_35
import com.wanted.android.wanted.design.util.OPACITY_5
import com.wanted.android.wanted.design.util.OPACITY_61
import com.wanted.android.wanted.design.util.OPACITY_88
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedIconButtonBackground(
    icon: DrawableResource,
    modifier: Modifier,
    alternative: Boolean = false,
    enabled: Boolean = true,
    tint: Color = DesignSystemTheme.colors.labelNormal
        .copy(alpha = if (alternative) OPACITY_88 else OPACITY_61),
    onClick: () -> Unit = {}
) {
    val contentSize = remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    val disabledCircleColor = DesignSystemTheme.colors.fillAlternative
    val alternativeCircleColor = DesignSystemTheme.colors.labelNormal.copy(alpha = 0.08f)
    val defaultOuterCircleColor = DesignSystemTheme.colors.staticWhite.copy(OPACITY_35)
    val defaultInnerCircleColor = DesignSystemTheme.colors.staticBlack.copy(OPACITY_5)
    val disabledTint = DesignSystemTheme.colors.labelNormal.copy(alpha = OPACITY_22)

    WantedTouchArea(
        content = {
            Icon(
                modifier = Modifier
                    .wantedBackground(
                        enabled = enabled,
                        alternative = alternative,
                        padding = 4.dp,
                        size = contentSize.value,
                        disabledCircleColor = disabledCircleColor,
                        alternativeCircleColor = alternativeCircleColor,
                        defaultOuterCircleColor = defaultOuterCircleColor,
                        defaultInnerCircleColor = defaultInnerCircleColor
                    )
                    .clip(CircleShape)
                    .then(modifier)
                    .onGloballyPositioned { coordinates ->
                        contentSize.value = with(localDensity) { coordinates.size.width.toDp() }
                    }
                    .padding(2.dp),
                painter = painterResource(icon),
                contentDescription = "",
                tint = if (enabled) tint else disabledTint
            )
        },
        enabled = enabled,
        shape = CircleShape,
        horizontalPadding = 4.dp,
        verticalPadding = 4.dp,
        onClick = onClick
    )
}

@Composable
private fun Modifier.wantedBackground(
    alternative: Boolean = false,
    enabled: Boolean = true,
    padding: Dp,
    size: Dp,
    disabledCircleColor: Color,
    alternativeCircleColor: Color,
    defaultOuterCircleColor: Color,
    defaultInnerCircleColor: Color
): Modifier {
    val localDensity = LocalDensity.current
    val modifier = when {
        !enabled -> {
            Modifier.drawBehind {
                drawCircle(
                    color = disabledCircleColor,
                    radius = with(localDensity) { (size + padding * 2).toPx() } / 2,
                    center = center,
                    style = Fill
                )
            }
        }

        alternative -> {
            Modifier.drawBehind {
                drawCircle(
                    color = alternativeCircleColor,
                    radius = with(localDensity) { (size + padding * 2).toPx() } / 2,
                    center = center,
                    style = Fill
                )
            }
        }

        else -> {
            Modifier.drawBehind {
                drawCircle(
                    color = defaultOuterCircleColor,
                    radius = with(localDensity) { (size + padding * 2).toPx() } / 2,
                    center = center,
                    style = Fill
                )

                drawCircle(
                    color = defaultInnerCircleColor,
                    radius = with(localDensity) { (size + padding * 2).toPx() } / 2,
                    center = center,
                    style = Fill
                )
            }
        }
    }

    return this.then(modifier)
}

@DevicePreviews
@Composable
private fun WantedIconButtonBackgroundPreview() {
    DesignSystemTheme {
        WantedIconButtonBackground(
            modifier = Modifier.size(24.dp),
            icon = Res.drawable.icon_normal_company,
            onClick = {}
        )
    }
}
