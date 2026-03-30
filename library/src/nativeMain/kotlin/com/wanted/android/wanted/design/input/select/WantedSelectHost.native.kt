package com.wanted.android.wanted.design.input.select

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract

@Composable
internal actual fun WantedSelectBottomSheetHost(
    modifier: Modifier,
    isShow: Boolean,
    items: List<WantedSelectData>,
    confirmText: String,
    selectType: WantedSelectDefaults.SelectType,
    bottomSheetType: WantedModalContract.ModalType,
    selectedItem: WantedSelectData?,
    onSelect: (item: WantedSelectData) -> Unit,
    onDismissRequest: () -> Unit
) {
    // Native targets do not yet have a dedicated Wanted bottom-sheet host.
}

@Composable
internal actual fun WantedMultiSelectBottomSheetHost(
    modifier: Modifier,
    isShow: Boolean,
    items: List<WantedSelectData>,
    confirmText: String,
    selectType: WantedSelectDefaults.SelectType,
    dialogType: WantedModalContract.ModalType,
    selectedItemList: List<WantedSelectData>,
    onSelect: (itemList: List<WantedSelectData>) -> Unit,
    onDismissRequest: () -> Unit
) {
    // Native targets do not yet have a dedicated Wanted bottom-sheet host.
}
