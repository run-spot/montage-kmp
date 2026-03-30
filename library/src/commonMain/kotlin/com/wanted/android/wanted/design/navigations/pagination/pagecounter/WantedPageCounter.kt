package com.wanted.android.wanted.design.navigations.pagination.pagecounter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.navigations.pagination.pagecounter.WantedPaginationCounterDefaults.WantedPageCounterSize
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_28
import com.wanted.android.wanted.design.util.OPACITY_35
import com.wanted.android.wanted.design.util.OPACITY_52
import com.wanted.android.wanted.design.util.OPACITY_61

@Composable
fun WantedPageCounter(
    totalPageCount: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier,
    size: WantedPageCounterSize = WantedPageCounterSize.Normal,
    isAlternative: Boolean = false
) {
    PageCounterLayout(
        modifier = modifier,
        size = size,
        isAlternative = isAlternative,
        currentIndex = {
            Text(
                text = currentIndex.toString(),
                maxLines = 1,
                style = when (size) {
                    WantedPageCounterSize.Small -> DesignSystemTheme.typography.label2Bold
                    WantedPageCounterSize.Normal -> DesignSystemTheme.typography.body2Bold
                },
                color = DesignSystemTheme.colors.staticWhite
            )
        },
        totalPageCount = {
            Text(
                text = totalPageCount.toString(),
                maxLines = 1,
                style = when (size) {
                    WantedPageCounterSize.Small -> DesignSystemTheme.typography.label2Bold
                    WantedPageCounterSize.Normal -> DesignSystemTheme.typography.body2Bold
                },
                color = DesignSystemTheme.colors.staticWhite
            )
        }
    )
}

@Composable
private fun PageCounterLayout(
    size: WantedPageCounterSize,
    isAlternative: Boolean,
    currentIndex: @Composable () -> Unit,
    totalPageCount: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundModifier = if (isAlternative) {
        Modifier.background(DesignSystemTheme.colors.lineSolidNormal.copy(OPACITY_61))
    } else {
        Modifier
            .background(DesignSystemTheme.colors.staticWhite.copy(OPACITY_35))
            .background(DesignSystemTheme.colors.staticBlack.copy(OPACITY_28))
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .then(backgroundModifier)
            .padding(horizontal = size.paddingHorizontal, vertical = size.paddingVertical),
        horizontalArrangement = Arrangement.spacedBy(size.space),
        verticalAlignment = Alignment.CenterVertically
    ) {
        currentIndex()
        Text(
            modifier = Modifier.alpha(if (isAlternative) OPACITY_28 else OPACITY_52),
            text = "/",
            style = when (size) {
                WantedPageCounterSize.Small -> DesignSystemTheme.typography.label2Regular
                WantedPageCounterSize.Normal -> DesignSystemTheme.typography.body2Regular
            },
            color = DesignSystemTheme.colors.staticWhite
        )
        totalPageCount()
    }
}

@DevicePreviews
@Composable
private fun WantedPageCounterPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedPageCounter(totalPageCount = 10, currentIndex = 2, isAlternative = false)
                WantedPageCounter(totalPageCount = 10, currentIndex = 2, isAlternative = true)
            }
        }
    }
}
