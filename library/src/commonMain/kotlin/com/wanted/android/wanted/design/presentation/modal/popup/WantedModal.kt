package com.wanted.android.wanted.design.presentation.modal.popup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalSize
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.presentation.modal.view.WantedDialogLayout
import com.wanted.android.wanted.design.presentation.modal.view.WantedDialogTwoButtonImpl

@Composable
fun WantedModal(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    type: ModalType = ModalType.Flexible,
    properties: DialogProperties = DialogProperties(),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    topBar: @Composable (() -> Unit)? = null,
    positive: String? = null,
    negative: String? = null,
    onClickPositive: (() -> Unit)? = null,
    onClickNegative: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        WantedDialogTwoButtonImpl(
            modifier = modifier.then(wantedModalHeightModifier(type, WantedModalContract.MAX_MODAL_SIZE.dp)),
            shape = shape,
            topBar = topBar,
            positive = positive,
            negative = negative,
            onClickPositive = onClickPositive,
            onClickNegative = onClickNegative,
            content = content
        )
    }
}

@Composable
fun WantedModal(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    size: ModalSize = ModalSize.Medium,
    type: ModalType = ModalType.Flexible,
    properties: DialogProperties = DialogProperties(),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        WantedDialogLayout(
            modifier = modifier.then(wantedModalHeightModifier(type, WantedModalContract.MAX_MODAL_SIZE.dp)),
            modalSize = size,
            shape = shape,
            topBar = topBar,
            content = {
                Box(modifier = Modifier.padding(horizontal = size.contentPadding)) {
                    content()
                }
            },
            bottomBar = bottomBar
        )
    }
}

@Composable
fun WantedModal(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    size: ModalSize = ModalSize.Medium,
    type: ModalType = ModalType.Flexible,
    properties: DialogProperties = DialogProperties(),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    lazyContent: LazyListScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        WantedDialogLayout(
            modifier = modifier.then(wantedModalHeightModifier(type, WantedModalContract.MAX_MODAL_SIZE.dp)),
            modalSize = size,
            shape = shape,
            topBar = topBar,
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(size.contentPadding)
                ) {
                    lazyContent()
                }
            },
            bottomBar = bottomBar
        )
    }
}

@Composable
expect fun wantedModalHeightModifier(
    type: ModalType,
    maxHeight: Dp? = null
): Modifier
