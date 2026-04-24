package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalSize
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.theme.DesignSystemTheme

/**
 * Bottom sheet modal component.
 */
@Composable
expect fun WantedModalBottomSheet(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
    type: ModalType = ModalType.Flexible,
    modalSize: ModalSize = ModalSize.Medium,
    dismissOnClickOutside: Boolean = true,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
)
