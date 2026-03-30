package com.wanted.android.wanted.design.navigations.progresstracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews

@Composable
fun WantedProgressTrackerHorizontal(
    stepCount: Int,
    currentStep: Int,
    modifier: Modifier = Modifier,
    label: ((index: Int) -> String)? = null
) {
    WantedProgressTrackerHorizontalLayout(
        modifier = modifier,
        stepCount = stepCount,
        progress = {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                progress = {
                    when {
                        currentStep - 1 <= 0 -> 0f
                        else -> (1f / (stepCount - 1)) * (currentStep - 1)
                    }
                },
                trackColor = DesignSystemTheme.colors.lineSolidNormal,
                color = DesignSystemTheme.colors.primaryNormal
            )
        },
        step = { index ->
            WantedProgressTrackerHorizontalItem(
                step = "${index + 1}",
                completed = index < (currentStep - 1),
                enabled = index == (currentStep - 1),
                label = label?.invoke(index) ?: ""
            )
        }
    )
}

@Composable
private fun WantedProgressTrackerHorizontalLayout(
    stepCount: Int,
    progress: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    step: @Composable (index: Int) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        val progressPadding = remember(maxWidth, stepCount) {
            mutableStateOf(if (maxWidth != 0.dp && stepCount != 0) maxWidth / stepCount / 2 else 0.dp)
        }

        Box(
            modifier = Modifier
                .padding(horizontal = progressPadding.value)
                .fillMaxWidth()
                .height(20.dp),
            contentAlignment = Alignment.Center
        ) {
            progress()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(stepCount) { index ->
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    step(index)
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun WantedProgressTrackerPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedProgressTrackerHorizontal(stepCount = 4, label = { "${it + 1}단계" }, currentStep = 1)
                WantedProgressTrackerHorizontal(stepCount = 4, label = { "${it + 1}단계" }, currentStep = 2)
                WantedProgressTrackerHorizontal(stepCount = 4, label = { "${it + 1}단계" }, currentStep = 3)
                WantedProgressTrackerHorizontal(stepCount = 4, label = { "${it + 1}단계" }, currentStep = 4)
            }
        }
    }
}
