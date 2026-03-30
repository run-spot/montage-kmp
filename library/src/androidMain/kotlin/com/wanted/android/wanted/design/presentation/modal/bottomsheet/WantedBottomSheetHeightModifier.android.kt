package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import android.annotation.SuppressLint
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
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract

private const val DRAG_HANDLE_SIZE_DP = 19

@SuppressLint("ModifierFactoryExtensionFunction")
@Composable
internal fun wantedBottomSheetHeightModifier(
    type: WantedModalContract.ModalType,
    maxHeight: Dp? = null,
    windowInsets: WindowInsets = WantedTopAppBarDefaults.windowInsets
): Modifier {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val windowInset = with(density) { windowInsets.getTop(this).toDp() }
    val screenHeight = configuration.screenHeightDp.dp

    return when (type) {
        is WantedModalContract.ModalType.Fixed -> {
            Modifier.height(min(type.height, maxHeight ?: type.height))
        }

        is WantedModalContract.ModalType.FixedFullScreen -> {
            val height = screenHeight - windowInset
            Modifier.height(min(height, maxHeight ?: height))
        }

        is WantedModalContract.ModalType.FixedRatio -> {
            val height = screenHeight - windowInset - 10.dp - DRAG_HANDLE_SIZE_DP.dp
            val ratioHeight = (screenHeight.value * type.ratio).dp
            val result = min(ratioHeight, height)
            Modifier.height(min(result, maxHeight ?: result))
        }

        else -> {
            val result = screenHeight - windowInset - 10.dp - DRAG_HANDLE_SIZE_DP.dp
            Modifier.heightIn(max = min(result, maxHeight ?: result))
        }
    }
}
