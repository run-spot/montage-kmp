package com.wanted.android.wanted.design.input.picker.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wanted.android.wanted.design.base.WantedTouchArea
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WantedTimePicker(
    title: String,
    confirm: String,
    onClickConfirm: () -> Unit,
    onClickChangeMode: () -> Unit,
    onDismissRequest: () -> Unit,
    cancel: String? = null,
    isEnableClock: Boolean = true,
    initialHour: Int = 0,
    initialMinute: Int = 0,
    onClickCancel: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        WantedTimePickerContent(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(DesignSystemTheme.colors.backgroundElevatedNormal),
            isEnableClock = isEnableClock,
            initialHour = initialHour,
            initialMinute = initialMinute,
            title = title,
            confirm = confirm,
            cancel = cancel,
            onClickConfirm = onClickConfirm,
            onClickCancel = onClickCancel,
            onClickChangeMode = onClickChangeMode
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WantedTimePickerContent(
    title: String,
    confirm: String,
    onClickConfirm: () -> Unit,
    onClickChangeMode: () -> Unit,
    modifier: Modifier = Modifier,
    isEnableClock: Boolean = true,
    initialHour: Int = 0,
    initialMinute: Int = 0,
    cancel: String? = null,
    onClickCancel: () -> Unit = {},
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false,
    )

    TimePickerLayout(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = DesignSystemTheme.colors.labelAlternative
            )
        },
        content = {
            if (isEnableClock) {
                TimePicker(
                    state = timePickerState,
                    colors = getWantedTimePickerDefaults()
                )
            } else {
                TimeInput(
                    state = timePickerState,
                    colors = getWantedTimePickerDefaults(
                        timeSelectorSelectedContainerColor = DesignSystemTheme.colors.transparent,
                        timeSelectorSelectedContentColor = DesignSystemTheme.colors.labelNormal
                    )
                )
            }
        },
        modeChange = {
            TimepickerModeChangeButton(
                isEnableClock = isEnableClock,
                onClickChangeMode = onClickChangeMode
            )
        },
        cancel = {
            cancel?.let {
                TimepickerButton(
                    text = cancel,
                    onClick = onClickCancel
                )
            }
        },
        confirm = {
            TimepickerButton(
                text = confirm,
                onClick = onClickConfirm
            )
        }
    )
}

@Composable
private fun TimepickerButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    TextButton(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = DesignSystemTheme.colors.primaryNormal
            )
        },
        onClick = onClick
    )
}

@Composable
private fun TimepickerModeChangeButton(
    isEnableClock: Boolean,
    modifier: Modifier = Modifier,
    onClickChangeMode: () -> Unit = {},
) {
    WantedTouchArea(
        modifier = modifier,
        verticalPadding = 4.dp,
        horizontalPadding = 4.dp,
        shape = CircleShape,
        content = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                text = if (isEnableClock) "입력" else "시계",
                style = DesignSystemTheme.typography.label1Medium,
                color = DesignSystemTheme.colors.labelAlternative
            )
        },
        onClick = onClickChangeMode
    )
}

@Composable
private fun TimePickerLayout(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modeChange: @Composable () -> Unit,
    cancel: @Composable () -> Unit,
    confirm: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(top = 24.dp, bottom = 20.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        title()
        content()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            modeChange()
            Spacer(Modifier.weight(1f))
            cancel()
            confirm()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getWantedTimePickerDefaults(
    timeSelectorSelectedContainerColor: Color = DesignSystemTheme.colors.primaryNormal,
    timeSelectorSelectedContentColor: Color = DesignSystemTheme.colors.staticWhite,
) = TimePickerDefaults.colors(
    clockDialColor = DesignSystemTheme.colors.fillAlternative,
    clockDialSelectedContentColor = DesignSystemTheme.colors.staticWhite,
    clockDialUnselectedContentColor = DesignSystemTheme.colors.labelNormal,
    selectorColor = DesignSystemTheme.colors.primaryNormal,
    containerColor = DesignSystemTheme.colors.backgroundElevatedNormal,
    periodSelectorBorderColor = DesignSystemTheme.colors.lineNormalNormal,
    periodSelectorSelectedContainerColor = DesignSystemTheme.colors.fillNormal,
    periodSelectorUnselectedContainerColor = DesignSystemTheme.colors.backgroundElevatedNormal,
    periodSelectorSelectedContentColor = DesignSystemTheme.colors.labelNormal,
    periodSelectorUnselectedContentColor = DesignSystemTheme.colors.labelAlternative,
    timeSelectorSelectedContainerColor = timeSelectorSelectedContainerColor,
    timeSelectorUnselectedContainerColor = DesignSystemTheme.colors.fillNormal,
    timeSelectorSelectedContentColor = timeSelectorSelectedContentColor,
    timeSelectorUnselectedContentColor = DesignSystemTheme.colors.labelNormal
)

@DevicePreviews
@Composable
private fun WantedTimePickerPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .background(DesignSystemTheme.colors.backgroundElevatedNormal),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                WantedTimePickerContent(
                    isEnableClock = true,
                    initialHour = 9,
                    initialMinute = 30,
                    title = "시간선택",
                    cancel = "취소",
                    confirm = "확인",
                    onClickConfirm = {},
                    onClickCancel = {},
                    onClickChangeMode = {}
                )

                WantedTimePickerContent(
                    isEnableClock = false,
                    initialHour = 9,
                    initialMinute = 30,
                    title = "시간선택",
                    cancel = "취소",
                    confirm = "확인",
                    onClickConfirm = {},
                    onClickCancel = {},
                    onClickChangeMode = {}
                )
            }
        }
    }
}
