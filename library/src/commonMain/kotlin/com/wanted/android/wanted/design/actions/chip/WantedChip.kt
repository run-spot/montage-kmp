package com.wanted.android.wanted.design.actions.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.ChipSize
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.ChipVariant
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.chipIconSize
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.chipPadding
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.chipTextPadding
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.getChipHorizontalArrangement
import com.wanted.android.wanted.design.actions.chip.WantedChipContract.getchipRadius
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_bookmark
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_12
import com.wanted.android.wanted.design.util.OPACITY_22
import com.wanted.android.wanted.design.util.OPACITY_5
import com.wanted.android.wanted.design.util.clickOnce
import com.wanted.android.wanted.design.util.wantedRippleEffect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedChipResource(
    text: String,
    modifier: Modifier = Modifier,
    size: ChipSize = ChipSize.Medium,
    variant: ChipVariant = ChipVariant.Solid,
    isActive: Boolean = false,
    isEnable: Boolean = true,
    leftIconResource: DrawableResource? = null,
    rightIconResource: DrawableResource? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (() -> Unit)? = null
) {
    CompositionLocalProvider(
        LocalWantedChipEnable.provides(isEnable),
        LocalWantedChipSize.provides(size),
        LocalWantedChipVariant.provides(variant),
        LocalWantedChipActive.provides(isActive)
    ) {
        WantedChip(
            text = text,
            modifier = modifier,
            interactionSource = interactionSource,
            chipDefault = WantedChipDefaults.getDefault(),
            leftIcon = leftIconResource?.let { icon ->
                {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(color = WantedChipDefaults.getDefault().iconColor)
                    )
                }
            },
            rightIcon = rightIconResource?.let { icon ->
                {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(color = WantedChipDefaults.getDefault().iconColor)
                    )
                }
            },
            onClick = onClick
        )
    }
}

@Composable
fun WantedChip(
    text: String,
    modifier: Modifier = Modifier,
    chipDefault: WantedChipDefault = WantedChipDefaults.getDefault(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    WantedChip(
        modifier = modifier,
        interactionSource = interactionSource,
        size = chipDefault.size,
        variant = chipDefault.variant,
        isActive = chipDefault.isActive,
        isEnable = chipDefault.isEnable,
        chipDefault = chipDefault,
        leftIcon = leftIcon,
        content = {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        rightIcon = rightIcon,
        onClick = onClick
    )
}

@Composable
fun WantedChip(
    modifier: Modifier = Modifier,
    size: ChipSize = ChipSize.Small,
    variant: ChipVariant = ChipVariant.Solid,
    isActive: Boolean = false,
    isEnable: Boolean = true,
    chipDefault: WantedChipDefault = WantedChipDefaults.getDefault(
        size = size,
        variant = variant,
        isActive = isActive,
        isEnable = isEnable
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit = {},
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    WantedChipLayout(
        modifier = modifier
            .clip(RoundedCornerShape(getchipRadius(chipDefault.size)))
            .background(chipDefault.backgroundColor)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(getchipRadius(chipDefault.size)),
                color = chipDefault.borderColor
            )
            .clickOnce(
                interactionSource = interactionSource,
                indication = if (chipDefault.variant == ChipVariant.Solid) {
                    wantedRippleEffect(color = DesignSystemTheme.colors.labelNormal.copy(OPACITY_12))
                } else {
                    wantedRippleEffect(color = chipDefault.backgroundColor.copy(OPACITY_12))
                },
                enabled = chipDefault.isEnable && onClick != null
            ) {
                onClick?.invoke()
            },
        chipDefault = chipDefault,
        leftIcon = leftIcon,
        content = content,
        rightIcon = rightIcon
    )
}

@Composable
private fun WantedChipLayout(
    modifier: Modifier,
    chipDefault: WantedChipDefault,
    content: @Composable () -> Unit,
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.chipPadding(size = chipDefault.size),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(getChipHorizontalArrangement(size = chipDefault.size))
    ) {
        leftIcon?.let {
            Box(
                modifier = Modifier.chipIconSize(chipDefault.size),
                contentAlignment = Alignment.Center
            ) {
                leftIcon()
            }
        }

        ProvideTextStyle(value = chipDefault.textStyle) {
            Box(
                modifier = Modifier
                    .chipTextPadding(chipDefault.size)
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }

        rightIcon?.let {
            Box(
                modifier = Modifier.chipIconSize(chipDefault.size),
                contentAlignment = Alignment.Center
            ) {
                rightIcon()
            }
        }
    }
}

@DevicePreviews
@Composable
private fun ChipPreView() {
    DesignSystemTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DesignSystemTheme.colors.backgroundNormalNormal
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChip(text = "텍스트", chipDefault = WantedChipDefaults.getDefault(variant = ChipVariant.Solid, size = ChipSize.Small))
                    WantedChip(text = "텍스트", chipDefault = WantedChipDefaults.getDefault(variant = ChipVariant.Solid, isEnable = false, size = ChipSize.Small))
                    WantedChip(text = "텍스트", chipDefault = WantedChipDefaults.getDefault(variant = ChipVariant.Outlined, size = ChipSize.Small))
                    WantedChip(text = "텍스트", chipDefault = WantedChipDefaults.getDefault(variant = ChipVariant.Outlined, isEnable = false, size = ChipSize.Small))
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, isEnable = false, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, isEnable = false, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, size = ChipSize.Small, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, isEnable = false, size = ChipSize.Small, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, size = ChipSize.Small, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, isEnable = false, size = ChipSize.Small, rightIconResource = Res.drawable.icon_normal_bookmark)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Solid, isEnable = false, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark, rightIconResource = Res.drawable.icon_normal_bookmark)
                    WantedChipResource(text = "텍스트", variant = ChipVariant.Outlined, isEnable = false, size = ChipSize.Small, leftIconResource = Res.drawable.icon_normal_bookmark, rightIconResource = Res.drawable.icon_normal_bookmark)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChip(
                        text = "this is very very long long long long long long text",
                        chipDefault = WantedChipDefaults.getDefault(variant = ChipVariant.Solid, size = ChipSize.Medium),
                        modifier = Modifier.widthIn(max = 200.dp)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    WantedChip(
                        text = "텍스트",
                        chipDefault = WantedChipDefaults.getDefault(
                            variant = ChipVariant.Solid,
                            isActive = false,
                            size = ChipSize.Small,
                            isEnable = true,
                            textStyle = DesignSystemTheme.typography.label1Medium.copy(DesignSystemTheme.colors.statusNegative),
                            iconColor = DesignSystemTheme.colors.statusNegative,
                            backgroundColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12)
                        ),
                        leftIcon = {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(Res.drawable.icon_normal_bookmark),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(color = DesignSystemTheme.colors.statusNegative)
                            )
                        },
                        rightIcon = {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(Res.drawable.icon_normal_bookmark),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(color = DesignSystemTheme.colors.statusNegative)
                            )
                        }
                    )
                    WantedChip(
                        text = "텍스트",
                        chipDefault = WantedChipDefaults.getDefault(
                            variant = ChipVariant.Outlined,
                            isActive = false,
                            size = ChipSize.Small,
                            isEnable = true,
                            textStyle = DesignSystemTheme.typography.label1Medium.copy(DesignSystemTheme.colors.statusNegative),
                            iconColor = DesignSystemTheme.colors.statusNegative,
                            backgroundColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12),
                            borderColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_22)
                        )
                    )
                    WantedChip(
                        text = "텍스트",
                        chipDefault = WantedChipDefaults.getDefault(
                            variant = ChipVariant.Outlined,
                            isActive = false,
                            size = ChipSize.Small,
                            isEnable = false,
                            textStyle = DesignSystemTheme.typography.label1Medium.copy(DesignSystemTheme.colors.statusNegative.copy(OPACITY_12)),
                            iconColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12),
                            backgroundColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_5),
                            borderColor = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12)
                        ),
                        leftIcon = {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(Res.drawable.icon_normal_bookmark),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(color = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12))
                            )
                        },
                        rightIcon = {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(Res.drawable.icon_normal_bookmark),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(color = DesignSystemTheme.colors.statusNegative.copy(OPACITY_12))
                            )
                        }
                    )
                }
            }
        }
    }
}
