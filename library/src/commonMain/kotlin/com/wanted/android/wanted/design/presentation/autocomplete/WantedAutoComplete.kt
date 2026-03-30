package com.wanted.android.wanted.design.presentation.autocomplete

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxScope.WantedAutoComplete(
    expanded: Boolean,
    onDismissRequest: (Boolean) -> Unit,
    sectionCount: Int,
    sectionItemCount: (section: Int) -> Int,
    sectionItem: @Composable (section: Int, index: Int) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
    sectionTitleHorizontalPadding: Dp = 20.dp,
    sectionTitle: ((section: Int) -> String)? = null,
    anchorPadding: Dp = 0.dp,
    topDirectInput: @Composable (() -> Unit)? = null,
    bottomDirectInput: @Composable (() -> Unit)? = null
) {
    val scrollState = rememberScrollState()

    val topDirectInputSize = remember { mutableIntStateOf(0) }
    val currentSection = remember { mutableIntStateOf(-1) }
    val sectionOffsets = remember { mutableStateMapOf<Int, Int>() }
    val density = LocalDensity.current
    val title = remember { mutableStateOf("") }

    LaunchedEffect(currentSection.intValue) {
        title.value = if (currentSection.intValue == -1) {
            ""
        } else {
            sectionTitle?.invoke(currentSection.intValue) ?: ""
        }
    }

    LaunchedEffect(scrollState.value, title.value) {
        currentSection.intValue = sectionOffsets.filter {
            it.value <= (scrollState.value + with(density) {
                4.dp.toPx() + if (title.value.isEmpty()) 0.dp.toPx() else 8.dp.toPx()
            })
        }.maxOfOrNull { it.key } ?: -1
    }

    DropdownMenu(
        modifier = modifier.padding(horizontal = 8.dp),
        scrollState = scrollState,
        containerColor = containerColor,
        shape = RoundedCornerShape(16.dp),
        expanded = expanded,
        shadowElevation = 1.dp,
        border = BorderStroke(1.dp, DesignSystemTheme.colors.lineSolidNormal),
        offset = DpOffset(x = 0.dp, y = anchorPadding),
        properties = PopupProperties(focusable = false),
        onDismissRequest = { onDismissRequest(false) }
    ) {
        topDirectInput?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { topDirectInputSize.intValue = it.size.height }
                    .zIndex(1000f)
                    .background(containerColor)
            ) {
                it()
                Spacer(modifier = Modifier.size(4.dp))
            }
        }

        if (title.value.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(0, scrollState.value - topDirectInputSize.intValue) }
                    .zIndex(1001f)
                    .background(containerColor)
                    .padding(horizontal = sectionTitleHorizontalPadding)
                    .padding(horizontal = 1.dp)
                    .padding(vertical = 4.dp),
                text = title.value,
                style = DesignSystemTheme.typography.caption1Bold,
                color = DesignSystemTheme.colors.labelAlternative
            )

            Spacer(modifier = Modifier.size(4.dp))
        }

        repeat(sectionCount) { section ->
            if (section != 0 || title.value.isEmpty()) {
                sectionTitle?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned {
                                sectionOffsets[section] = it.positionInParent().y.toInt()
                            }
                            .padding(horizontal = sectionTitleHorizontalPadding)
                            .padding(horizontal = 1.dp)
                            .padding(vertical = 4.dp),
                        text = sectionTitle(section),
                        style = DesignSystemTheme.typography.caption1Bold,
                        color = DesignSystemTheme.colors.labelAlternative
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))
            }

            val itemCount = sectionItemCount(section)
            repeat(itemCount) { index ->
                sectionItem(section, index)
                if (itemCount != index - 1) {
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }

        bottomDirectInput?.let {
            it()
        }
    }
}
