package com.wanted.android.wanted.design.input.filterbutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.FilterButtonSize
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.FilterButtonVariant
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.filterButtonIconSize
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.filterButtonPadding
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.filterButtonTextPadding
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.getFilterButtonHorizontalArrangement
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.getFilterButtonRadius
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_caret_down
import com.wanted.android.wanted.design.resources.icon_normal_caret_up
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_12
import com.wanted.android.wanted.design.util.clickOnce
import com.wanted.android.wanted.design.util.wantedRippleEffect
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedFilterButton(
    text: String,
    modifier: Modifier = Modifier,
    activeLabel: String = "",
    size: FilterButtonSize = FilterButtonSize.Small,
    variant: FilterButtonVariant = FilterButtonVariant.Solid,
    isActive: Boolean = false,
    isEnable: Boolean = true,
    isExpend: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (() -> Unit)? = null,
) {
    CompositionLocalProvider(
        LocalWantedFilterButtonEnable.provides(isEnable),
        LocalWantedFilterButtonSize.provides(size),
        LocalWantedFilterButtonVariant.provides(variant),
        LocalWantedFilterButtonActive.provides(isActive)
    ) {
        WantedFilterButton(
            modifier = modifier,
            text = text,
            activeLabel = activeLabel,
            filterButtonDefault = WantedFilterButtonDefaults
                .getDefault()
                .copy(iconColor = WantedFilterButtonDefaults.getFilterIconColor()),
            isExpanded = isExpend,
            interactionSource = interactionSource,
            onClick = onClick
        )
    }
}

@Composable
fun WantedFilterButton(
    text: String,
    modifier: Modifier = Modifier,
    activeLabel: String = "",
    isExpanded: Boolean = false,
    filterButtonDefault: WantedFilterButtonDefault = WantedFilterButtonDefaults.getDefault(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (() -> Unit)? = null,
) {
    WantedFilterButton(
        modifier = modifier,
        interactionSource = interactionSource,
        size = filterButtonDefault.size,
        variant = filterButtonDefault.variant,
        isActive = filterButtonDefault.isActive,
        isEnable = filterButtonDefault.isEnabled,
        filterButtonDefault = filterButtonDefault,
        content = {
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f, fill = false),
                    text = text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (activeLabel.isNotEmpty()) {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = activeLabel,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = filterButtonDefault.textStyle.merge(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        },
        rightIcon = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = if (isExpanded) {
                    painterResource(Res.drawable.icon_normal_caret_up)
                } else {
                    painterResource(Res.drawable.icon_normal_caret_down)
                },
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(color = filterButtonDefault.iconColor)
            )
        },
        onClick = onClick
    )
}

@Composable
private fun WantedFilterButton(
    modifier: Modifier = Modifier,
    size: FilterButtonSize = FilterButtonSize.Small,
    variant: FilterButtonVariant = FilterButtonVariant.Solid,
    isActive: Boolean = false,
    isEnable: Boolean = true,
    filterButtonDefault: WantedFilterButtonDefault = WantedFilterButtonDefaults.getDefault(
        size = size,
        variant = variant,
        isActive = isActive,
        isEnable = isEnable
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
    rightIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    WantedFilterButtonLayout(
        modifier = modifier
            .clip(RoundedCornerShape(getFilterButtonRadius(filterButtonDefault.size)))
            .background(filterButtonDefault.backgroundColor)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(getFilterButtonRadius(filterButtonDefault.size)),
                color = filterButtonDefault.borderColor
            )
            .clickOnce(
                interactionSource = interactionSource,
                indication = if (filterButtonDefault.variant == FilterButtonVariant.Solid) {
                    wantedRippleEffect(
                        color = DesignSystemTheme.colors.labelNormal.copy(OPACITY_12)
                    )
                } else {
                    wantedRippleEffect(
                        color = filterButtonDefault.backgroundColor.copy(OPACITY_12)
                    )
                },
                enabled = filterButtonDefault.isEnabled
            ) {
                onClick?.invoke()
            },
        filterButtonDefault = filterButtonDefault,
        content = content,
        rightIcon = rightIcon
    )
}

@Composable
private fun WantedFilterButtonLayout(
    filterButtonDefault: WantedFilterButtonDefault,
    content: @Composable () -> Unit,
    rightIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier,
) {
    Row(
        modifier = modifier.filterButtonPadding(size = filterButtonDefault.size),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            getFilterButtonHorizontalArrangement(size = filterButtonDefault.size)
        )
    ) {
        ProvideTextStyle(value = filterButtonDefault.textStyle) {
            Box(
                modifier = Modifier
                    .filterButtonTextPadding(filterButtonDefault.size)
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }

        rightIcon?.let {
            Box(
                modifier = Modifier.filterButtonIconSize(filterButtonDefault.size),
                contentAlignment = Alignment.Center
            ) {
                rightIcon()
            }
        }
    }
}

@DevicePreviews
@Composable
private fun FilterButtonPreView() {
    DesignSystemTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DesignSystemTheme.colors.backgroundNormalNormal
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WantedFilterButton(
                        text = "텍스트",
                        variant = FilterButtonVariant.Solid,
                        size = FilterButtonSize.Medium
                    )
                    WantedFilterButton(
                        text = "텍스트",
                        variant = FilterButtonVariant.Outlined,
                        isExpend = true,
                        size = FilterButtonSize.Medium
                    )
                }
            }
        }
    }
}
