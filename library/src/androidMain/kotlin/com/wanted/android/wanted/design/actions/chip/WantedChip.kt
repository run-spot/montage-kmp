@file:kotlin.jvm.JvmName("WantedChipAndroidKt")

package com.wanted.android.wanted.design.actions.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun WantedChip(
    text: String,
    modifier: Modifier = Modifier,
    size: WantedChipContract.ChipSize = WantedChipContract.ChipSize.Medium,
    variant: WantedChipContract.ChipVariant = WantedChipContract.ChipVariant.Solid,
    isActive: Boolean = false,
    isEnable: Boolean = true,
    leftIcon: Int? = null,
    rightIcon: Int? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (() -> Unit)? = null
) {
    CompositionLocalProvider(
        LocalWantedChipEnable.provides(isEnable),
        LocalWantedChipSize.provides(size),
        LocalWantedChipVariant.provides(variant),
        LocalWantedChipActive.provides(isActive)
    ) {
        val chipDefault = WantedChipDefaults.getDefault()
        val leftIconContent: (@Composable () -> Unit)? = leftIcon?.let { iconId ->
            @Composable {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(color = chipDefault.iconColor)
                )
            }
        }
        val rightIconContent: (@Composable () -> Unit)? = rightIcon?.let { iconId ->
            @Composable {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(color = chipDefault.iconColor)
                )
            }
        }
        WantedChip(
            text = text,
            modifier = modifier,
            interactionSource = interactionSource,
            chipDefault = chipDefault,
            leftIcon = leftIconContent,
            rightIcon = rightIconContent,
            onClick = onClick
        )
    }
}
