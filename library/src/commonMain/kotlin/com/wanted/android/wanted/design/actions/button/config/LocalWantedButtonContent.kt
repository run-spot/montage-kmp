package com.wanted.android.wanted.design.actions.button.config
import kotlin.jvm.JvmInline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant


val LocalWantedButtonContent = WantedButtonContentCompositionLocal()

interface WantedButtonContentLoader {
    @Composable
    fun getContentColor(
        shape: ButtonVariant,
        type: ButtonType,
        enabled: Boolean
    ): Color

    @Composable
    fun getBackgroundColor(
        variant: ButtonVariant,
        type: ButtonType,
        enabled: Boolean
    ): Color
}

internal class WantedButtonContentLoaderImpl : WantedButtonContentLoader {
    @Composable
    override fun getContentColor(
        shape: ButtonVariant,
        type: ButtonType,
        enabled: Boolean
    ): Color = when (shape) {
        ButtonVariant.SOLID -> getSolidContentColor(type, enabled)
        ButtonVariant.OUTLINED -> getOutlineContentColor(type, enabled)
        ButtonVariant.TEXT -> getTextContentColor(type, enabled)
    }

    @Composable
    fun getSolidContentColor(
        type: ButtonType,
        enabled: Boolean
    ): Color = when {
        !enabled -> DesignSystemTheme.colors.labelAssistive
        type == ButtonType.ASSISTIVE -> DesignSystemTheme.colors.labelNeutral
        else -> DesignSystemTheme.colors.staticWhite
    }

    @Composable
    fun getOutlineContentColor(
        type: ButtonType,
        enabled: Boolean
    ) = when {
        !enabled -> DesignSystemTheme.colors.labelDisable
        type == ButtonType.ASSISTIVE -> DesignSystemTheme.colors.labelNormal
        else -> DesignSystemTheme.colors.primaryNormal
    }

    @Composable
    fun getTextContentColor(
        type: ButtonType,
        enabled: Boolean
    ) = when {
        !enabled -> DesignSystemTheme.colors.labelDisable
        type == ButtonType.ASSISTIVE -> DesignSystemTheme.colors.labelAlternative
        else -> DesignSystemTheme.colors.primaryNormal
    }

    @Composable
    override fun getBackgroundColor(
        variant: ButtonVariant,
        type: ButtonType,
        enabled: Boolean
    ): Color = when (variant) {
        ButtonVariant.SOLID -> getSolidBackgroundColor(type, enabled)
        ButtonVariant.OUTLINED -> DesignSystemTheme.colors.transparent
        ButtonVariant.TEXT -> DesignSystemTheme.colors.transparent
    }

    @Composable
    fun getSolidBackgroundColor(
        type: ButtonType,
        enabled: Boolean
    ): Color = when {
        !enabled -> DesignSystemTheme.colors.interactionDisable
        type == ButtonType.ASSISTIVE -> DesignSystemTheme.colors.fillNormal
        else -> DesignSystemTheme.colors.primaryNormal
    }
}

@JvmInline
value class WantedButtonContentCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<WantedButtonContentLoader> = staticCompositionLocalOf { WantedButtonContentLoaderImpl() }
) {
    val current: WantedButtonContentLoader
        @Composable get() = delegate.current

    infix fun provides(value: WantedButtonContentLoader) = delegate provides value
}
