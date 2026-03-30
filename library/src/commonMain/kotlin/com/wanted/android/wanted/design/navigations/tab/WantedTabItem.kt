package com.wanted.android.wanted.design.navigations.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews

@Composable
fun WantedTabItem(
    tabSize: WantedTabDefaults.TabSize,
    title: String,
    active: Boolean,
    enable: Boolean,
    modifier: Modifier = Modifier,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    onClick: () -> Unit
) {
    WantedTouchArea(
        modifier = modifier,
        enabled = enable,
        content = {
            Text(
                text = title,
                style = when (tabSize) {
                    WantedTabDefaults.TabSize.Large -> DesignSystemTheme.typography.heading2Bold
                    WantedTabDefaults.TabSize.Medium -> DesignSystemTheme.typography.headline2Bold
                    else -> DesignSystemTheme.typography.body2Bold
                },
                color = when {
                    !enable -> DesignSystemTheme.colors.labelDisable
                    active -> DesignSystemTheme.colors.labelStrong
                    else -> DesignSystemTheme.colors.labelAssistive
                },
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { onTextLayout?.invoke(it) }
            )
        },
        horizontalPadding = 12.dp,
        verticalPadding = if (tabSize == WantedTabDefaults.TabSize.Large) 14.dp else 12.dp,
        isUseRipple = false,
        rippleColor = DesignSystemTheme.colors.transparent,
        onClick = onClick
    )
}

@DevicePreviews
@Composable
private fun WantedTabItemPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Small, title = "텍스트", active = false, enable = true, onClick = {})
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Small, title = "텍스트", active = true, enable = true, onClick = {})
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Medium, title = "텍스트", active = false, enable = true, onClick = {})
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Medium, title = "텍스트", active = true, enable = true, onClick = {})
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Large, title = "텍스트", active = false, enable = true, onClick = {})
                WantedTabItem(tabSize = WantedTabDefaults.TabSize.Large, title = "텍스트", active = true, enable = true, onClick = {})
            }
        }
    }
}
