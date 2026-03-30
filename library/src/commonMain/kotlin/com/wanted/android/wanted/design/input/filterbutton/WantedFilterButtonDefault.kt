package com.wanted.android.wanted.design.input.filterbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.FilterButtonSize
import com.wanted.android.wanted.design.input.filterbutton.WantedFilterButtonContract.FilterButtonVariant
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_43
import com.wanted.android.wanted.design.util.OPACITY_5

data class WantedFilterButtonDefault(
    val size: FilterButtonSize = FilterButtonSize.Medium,
    val variant: FilterButtonVariant = FilterButtonVariant.Solid,
    val isActive: Boolean = false,
    val isEnabled: Boolean = true,
    val iconColor: Color,
    val backgroundColor: Color,
    val borderColor: Color,
    val textStyle: TextStyle
)

object WantedFilterButtonDefaults {
    @Composable
    fun getDefault(
        size: FilterButtonSize = LocalWantedFilterButtonSize.current,
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current,
        iconColor: Color = getIconColor(
            variant = variant,
            isActive = isActive,
            isEnable = isEnable
        ),
        backgroundColor: Color = getBackgroundColor(
            variant = variant,
            isActive = isActive,
            isEnable = isEnable
        ),
        borderColor: Color = getBorderColor(
            variant = variant,
            isActive = isActive,
            isEnable = isEnable
        ),
        textStyle: TextStyle = getTextStyle(
            variant = variant,
            size = size,
            isActive = isActive,
            isEnable = isEnable
        )
    ) = WantedFilterButtonDefault(
        size = size,
        variant = variant,
        isActive = isActive,
        isEnabled = isEnable,
        iconColor = iconColor,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        textStyle = textStyle
    )

    @Composable
    private fun getIconColor(
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): Color {
        return when (variant) {
            FilterButtonVariant.Solid -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.inverseLabel
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }

            FilterButtonVariant.Outlined -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.primaryNormal
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }
        }
    }

    @Composable
    fun getFilterIconColor(
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): Color {
        return when (variant) {
            FilterButtonVariant.Solid -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.inverseLabel
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }

            FilterButtonVariant.Outlined -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.labelNormal
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }
        }
    }

    @Composable
    private fun getBackgroundColor(
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): Color = when (variant) {
        FilterButtonVariant.Solid -> {
            when {
                !isEnable -> DesignSystemTheme.colors.interactionDisable
                isActive -> DesignSystemTheme.colors.inverseBackground
                else -> DesignSystemTheme.colors.fillAlternative
            }
        }

        FilterButtonVariant.Outlined -> {
            when {
                !isEnable -> DesignSystemTheme.colors.transparent
                isActive -> DesignSystemTheme.colors.primaryNormal.copy(alpha = OPACITY_5)
                else -> DesignSystemTheme.colors.transparent
            }
        }
    }

    @Composable
    private fun getBorderColor(
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): Color = when (variant) {
        FilterButtonVariant.Solid -> DesignSystemTheme.colors.transparent
        FilterButtonVariant.Outlined -> {
            when {
                !isEnable -> DesignSystemTheme.colors.lineNormalNeutral
                isActive -> DesignSystemTheme.colors.primaryNormal.copy(alpha = OPACITY_43)
                else -> DesignSystemTheme.colors.lineNormalNeutral
            }
        }
    }

    @Composable
    private fun getTextStyle(
        size: FilterButtonSize = LocalWantedFilterButtonSize.current,
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): TextStyle = getChipFilterTextStyle(size).copy(
        color = getChipFilterTextColor(variant, isActive, isEnable)
    )

    @Composable
    private fun getChipFilterTextColor(
        variant: FilterButtonVariant = LocalWantedFilterButtonVariant.current,
        isActive: Boolean = LocalWantedFilterButtonActive.current,
        isEnable: Boolean = LocalWantedFilterButtonEnable.current
    ): Color {
        return when (variant) {
            FilterButtonVariant.Solid -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.inverseLabel
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }

            FilterButtonVariant.Outlined -> {
                when {
                    !isEnable -> DesignSystemTheme.colors.labelDisable
                    isActive -> DesignSystemTheme.colors.primaryNormal
                    else -> DesignSystemTheme.colors.labelNormal
                }
            }
        }
    }

    @Composable
    private fun getChipFilterTextStyle(
        size: FilterButtonSize = LocalWantedFilterButtonSize.current
    ): TextStyle = when (size) {
        FilterButtonSize.XSmall -> DesignSystemTheme.typography.caption1Medium
        FilterButtonSize.Small -> DesignSystemTheme.typography.label1Medium
        FilterButtonSize.Medium -> DesignSystemTheme.typography.body2Medium
        FilterButtonSize.Large -> DesignSystemTheme.typography.body2Medium
    }
}
