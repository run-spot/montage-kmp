package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WantedBottomSheetDragPolicyTest {
    @Test
    fun dismissesWhenOffsetPassesQuarterHeightOrDismissThreshold() {
        assertFalse(shouldDismissBottomSheet(sheetOffsetY = 20f, sheetHeight = 400f, dismissThreshold = 96f))
        assertTrue(shouldDismissBottomSheet(sheetOffsetY = 97f, sheetHeight = 400f, dismissThreshold = 96f))
        assertTrue(shouldDismissBottomSheet(sheetOffsetY = 81f, sheetHeight = 320f, dismissThreshold = 96f))
    }
}
