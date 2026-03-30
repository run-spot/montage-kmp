package com.wanted.android.wanted.design.presentation.modal.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType

@Composable
actual fun wantedModalHeightModifier(
    type: ModalType,
    maxHeight: Dp?
): Modifier {
    return Modifier
}
