package com.wanted.android.wanted.design.input.segmentedcontrol

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.clickOnce
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedSegmentedControlOutlined(
    items: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    size: WantedSegmentedDefaults.SegmentedSize = WantedSegmentedDefaults.SegmentedSize.Medium,
    onClick: (Int) -> Unit = {}
) {
    CompositionLocalProvider(LocalWantedSegmentedSize.provides(size)) {
        WantedSegmentedControlOutlined(
            modifier = modifier,
            size = size,
            itemCount = items.size,
            onClick = onClick,
            item = { index ->
                WantedSegmentedControlOutlinedItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = items[index],
                    isSelected = index == selectedIndex,
                    isFirst = index == 0,
                    isLast = index == items.lastIndex
                )
            }
        )
    }
}

@Composable
fun WantedSegmentedControlOutlined(
    itemCount: Int,
    item: @Composable (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    size: WantedSegmentedDefaults.SegmentedSize = WantedSegmentedDefaults.SegmentedSize.Medium,
    onClick: (index: Int) -> Unit = {}
) {
    CompositionLocalProvider(LocalWantedSegmentedSize.provides(size)) {
        Row(
            modifier = modifier
                .height(IntrinsicSize.Min)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = DesignSystemTheme.colors.lineNormalNormal
                ),
            horizontalArrangement = Arrangement.spacedBy((-1).dp)
        ) {
            repeat(itemCount) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickOnce(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onClick(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    item(index)
                }

                if (index != itemCount - 1) {
                    VerticalDivider(color = DesignSystemTheme.colors.lineNormalNormal)
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun WantedSegmentedControlOutlinedPreview() {
    DesignSystemTheme {
        val items = remember { List(3) { "텍스트${it + 1}" } }
        var selectedIndex by remember { mutableIntStateOf(0) }

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedSegmentedControlOutlined(
                    modifier = Modifier,
                    items = items,
                    selectedIndex = selectedIndex,
                    onClick = { selectedIndex = it }
                )

                WantedSegmentedControlOutlined(
                    modifier = Modifier,
                    itemCount = items.size,
                    onClick = { selectedIndex = it },
                    item = { index ->
                        WantedSegmentedControlOutlinedItem(
                            modifier = Modifier.fillMaxWidth(),
                            title = items[index],
                            isSelected = index == selectedIndex,
                            isFirst = index == 0,
                            isLast = index == items.lastIndex,
                            icon = {
                                Icon(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                                    contentDescription = ""
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
