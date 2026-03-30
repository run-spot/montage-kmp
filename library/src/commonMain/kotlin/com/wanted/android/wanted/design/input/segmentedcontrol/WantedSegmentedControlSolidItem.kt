package com.wanted.android.wanted.design.input.segmentedcontrol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.segmentedcontrol.WantedSegmentedDefaults.SegmentedSize
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedSegmentedControlSolidItem(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    CompositionLocalProvider(
        value = LocalContentColor provides if (isSelected) {
            DesignSystemTheme.colors.labelNormal
        } else {
            DesignSystemTheme.colors.labelAlternative
        }
    ) {
        Box(
            modifier = modifier.padding(
                vertical = when (LocalWantedSegmentedSize.current) {
                    SegmentedSize.Small -> 5.dp
                    SegmentedSize.Medium -> 7.dp
                    SegmentedSize.Large -> 9.dp
                },
                horizontal = when (LocalWantedSegmentedSize.current) {
                    SegmentedSize.Small -> 6.dp
                    SegmentedSize.Medium -> 8.dp
                    SegmentedSize.Large -> 8.dp
                }
            ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                icon?.let {
                    Box(modifier = Modifier.size(20.dp)) { icon() }
                }

                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = title,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = when (LocalWantedSegmentedSize.current) {
                        SegmentedSize.Small -> DesignSystemTheme.typography.label2Medium
                        SegmentedSize.Medium -> DesignSystemTheme.typography.body2Medium
                        SegmentedSize.Large -> DesignSystemTheme.typography.headline2Medium
                    },
                    color = if (isSelected) {
                        DesignSystemTheme.colors.labelNormal
                    } else {
                        DesignSystemTheme.colors.labelAlternative
                    }
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun WantedSegmentedControlItemPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedSegmentedControlSolidItem(title = "타이틀", isSelected = false)
                WantedSegmentedControlSolidItem(title = "타이틀", isSelected = true)
                WantedSegmentedControlSolidItem(
                    title = "타이틀",
                    isSelected = false,
                    icon = {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )
                WantedSegmentedControlSolidItem(
                    title = "타이틀",
                    isSelected = true,
                    icon = {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}
