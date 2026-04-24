package com.wanted.android.wanted.design.input.select

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wanted.android.wanted.design.input.select.view.WantedMultiSelectBottomSheet
import com.wanted.android.wanted.design.input.select.view.WantedSelectBottomSheet
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
    WantedSelectBottomSheet(
        modifier = modifier,
        isShow = isShow,
        items = items,
        confirmText = confirmText,
        selectType = selectType,
        bottomSheetType = bottomSheetType,
        selectedItem = selectedItem,
        onSelect = onSelect,
        onDismissRequest = onDismissRequest
    )
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
    WantedMultiSelectBottomSheet(
        modifier = modifier,
        isShow = isShow,
        items = items,
        confirmText = confirmText,
        selectType = selectType,
        dialogType = dialogType,
        selectedItemList = selectedItemList,
        onSelect = onSelect,
        onDismissRequest = onDismissRequest
    )
}
