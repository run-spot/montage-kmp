package com.wanted.android.wanted.design.feedback.toast

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

data class WantedToastRequest(
    val text: String,
    val duration: ToastDuration = ToastDuration.Short,
    val padding: PaddingValues = PaddingValues(bottom = 20.dp),
    val variant: WantedToastVariant = WantedToastVariant.Message,
    val icon: (@androidx.compose.runtime.Composable () -> Unit)? = null
)
