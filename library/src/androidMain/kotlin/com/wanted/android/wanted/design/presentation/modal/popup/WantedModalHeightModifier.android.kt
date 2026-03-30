package com.wanted.android.wanted.design.presentation.modal.popup

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.wanted.android.wanted.design.navigations.topbar.WantedTopAppBarDefaults
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.util.pxToDp

@SuppressLint("ModifierFactoryExtensionFunction")
@Composable
actual fun wantedModalHeightModifier(
    type: ModalType,
    maxHeight: Dp?
): Modifier {
    val configuration: Configuration = LocalConfiguration.current
    val windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets

    val windowInset = windowInsets.getTop(LocalDensity.current).pxToDp()
    val screenHeight = configuration.screenHeightDp.dp

    return when (type) {
        is ModalType.Fixed -> {
            Modifier.height(min(type.height, maxHeight ?: type.height))
        }

        is ModalType.FixedFullScreen -> {
            val height = screenHeight - windowInset
            Modifier.height(min(height, maxHeight ?: height))
        }

        is ModalType.FixedRatio -> {
            val height = screenHeight - windowInset - 10.dp
            val ratioHeight = screenHeight * type.ratio
            val result = min(ratioHeight, height)

            Modifier.height(min(result, maxHeight ?: result))
        }

        else -> {
            val result = screenHeight - windowInset - 10.dp
            Modifier.heightIn(max = min(result, maxHeight ?: result))
        }
    }
}
