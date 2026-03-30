@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import androidx.compose.material3.SheetValue
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract

internal fun WantedModalContract.ModalType.skipPartiallyExpanded(): Boolean {
    return this !is WantedModalContract.ModalType.Flexible
}

internal fun WantedModalContract.ModalType.confirmSheetValueChange(
    sheetValue: SheetValue
): Boolean {
    return if (!isCloseable) {
        sheetValue == SheetValue.Expanded
    } else {
        true
    }
}

internal fun WantedModalContract.ModalType.shouldUseSystemBottomSheet(): Boolean {
    return isSystemBottomSheet
}

internal fun WantedModalContract.ModalType.shouldShowDragHandle(): Boolean {
    return isCloseable
}
