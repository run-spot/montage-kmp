package com.wanted.android.wanted.design.input.segmentedcontrol

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_43
import com.wanted.android.wanted.design.util.OPACITY_5

@Composable
fun WantedSegmentedControlOutlinedItem(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    icon: @Composable (() -> Unit)? = null
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (isSelected) {
            DesignSystemTheme.colors.primaryNormal.copy(alpha = OPACITY_5)
        } else {
            DesignSystemTheme.colors.transparent
        },
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    val borderColor = animateColorAsState(
        targetValue = if (isSelected) {
            DesignSystemTheme.colors.primaryNormal.copy(alpha = OPACITY_43)
        } else {
            DesignSystemTheme.colors.transparent
        },
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    val contentColor = if (isSelected) {
        DesignSystemTheme.colors.primaryNormal
    } else {
        DesignSystemTheme.colors.labelAlternative
    }

    CompositionLocalProvider(value = LocalContentColor provides contentColor) {
        Row(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = borderColor.value,
                    shape = when {
                        isFirst -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                        isLast -> RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        else -> RoundedCornerShape(0.dp)
                    }
                )
                .background(
                    color = backgroundColor.value,
                    shape = when {
                        isFirst -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                        isLast -> RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        else -> RoundedCornerShape(0.dp)
                    }
                )
                .padding(
                    vertical = when (LocalWantedSegmentedSize.current) {
                        SegmentedSize.Small -> 7.dp
                        SegmentedSize.Medium -> 9.dp
                        SegmentedSize.Large -> 12.dp
                    },
                    horizontal = when (LocalWantedSegmentedSize.current) {
                        SegmentedSize.Small -> 6.dp
                        SegmentedSize.Medium -> 8.dp
                        SegmentedSize.Large -> 8.dp
                    }
                )
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                androidx.compose.foundation.layout.Box(modifier = Modifier.size(20.dp)) {
                    icon()
                }
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
                color = contentColor
            )
        }
    }
}

@DevicePreviews
@Composable
private fun WantedSegmentedControlOutlinedItemPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedSegmentedControlOutlinedItem(title = "title", isSelected = true)
                WantedSegmentedControlOutlinedItem(title = "title", isSelected = false)
            }
        }
    }
}
