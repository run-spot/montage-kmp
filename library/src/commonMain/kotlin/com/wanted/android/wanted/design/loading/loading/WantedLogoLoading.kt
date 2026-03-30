package com.wanted.android.wanted.design.loading.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun WantedLogoLoading(
    modifier: Modifier = Modifier,
    isUseDim: Boolean = false
) {
    if (isUseDim) {
        WantedLogoLoadingDialog()
    } else {
        WantedLogoLoadingContent(modifier)
    }
}

@Composable
private fun WantedLogoLoadingContent(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            WantedLogoProgressIndicator()
        }
    }
}

@Composable
fun WantedLogoLoadingDialog(
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
        usePlatformDefaultWidth = false
    )
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        WantedLogoProgressIndicator()
    }
}
