package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalSize
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract.ModalType
import com.wanted.android.wanted.design.presentation.modal.view.WantedDialogLayout
import com.wanted.android.wanted.design.theme.DesignSystemTheme


@Deprecated("Use WantedModalBottomSheet")
@Composable
fun WantedBottomSheetDialog(
    isVisible: Boolean,
    modalSize: ModalSize = ModalSize.Medium,
    durationMillis: Long = 200,
    onDismissRequest: () -> Unit = {},
    topBar: @Composable () -> Unit,
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    WantedModalBottomSheet(
        isShow = isVisible,
        onDismissRequest = onDismissRequest,
        modalSize = modalSize,
        type = ModalType.Flexible,
        topBar = topBar,
        bottomBar = bottomBar,
        content = content
    )
}

@Deprecated("Use WantedModalBottomSheet")
@Composable
fun WantedBottomSheetLayout(
    modalSize: ModalSize,
    modifier: Modifier = Modifier,
    backgroundColor: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
    shadowElevation: Dp = 4.dp,
    topBar: @Composable (() -> Unit)?,
    bottomBar: @Composable (() -> Unit)?,
    content: @Composable () -> Unit
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        shadowElevation = shadowElevation,
        color = DesignSystemTheme.colors.backgroundNormalNormal
    ) {
        Column(Modifier.fillMaxWidth()) {
            WantedDialogLayout(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = backgroundColor,
                modalSize = modalSize,
                topBar = topBar,
                content = {
                    Box(modifier = Modifier.padding(horizontal = modalSize.contentPadding)) {
                        content()
                    }
                },
                bottomBar = bottomBar
            )

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                if (WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() > 0.dp) {
                    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
                } else {
                    Spacer(Modifier.size(48.dp))
                }
            } else {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }
        }
    }
}
