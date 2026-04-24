package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import androidx.compose.runtime.Composable
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract
import kotlin.test.Test

class WantedModalBottomSheetApiTest {
    @Test
    fun exposesWantedModalBottomSheetToCommonConsumers() {
        val content: @Composable () -> Unit = {
            WantedModalBottomSheet(
                isShow = true,
                onDismissRequest = {},
                type = WantedModalContract.ModalType.FixedWrapContent(),
                content = {}
            )
        }

        @Suppress("UNUSED_EXPRESSION")
        content
    }
}
