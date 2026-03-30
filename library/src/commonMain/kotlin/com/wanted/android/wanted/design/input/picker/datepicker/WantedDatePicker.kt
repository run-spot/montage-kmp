package com.wanted.android.wanted.design.input.picker.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WantedDatePicker(
    confirm: String,
    cancel: String,
    datePickerState: DatePickerState = rememberDatePickerState(),
    dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() },
    onDateSelected: (Long?) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        colors = getWantedDatePickerDefaults(),
        confirmButton = {
            TextButton(
                shape = RoundedCornerShape(10.dp),
                content = {
                    Text(
                        text = confirm,
                        style = MaterialTheme.typography.labelLarge,
                        color = DesignSystemTheme.colors.primaryNormal
                    )
                },
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            )
        },
        dismissButton = {
            TextButton(
                shape = RoundedCornerShape(10.dp),
                content = {
                    Text(
                        text = cancel,
                        style = MaterialTheme.typography.labelLarge,
                        color = DesignSystemTheme.colors.primaryNormal
                    )
                },
                onClick = onDismiss
            )
        }
    ) {
        DatePicker(
            state = datePickerState,
            dateFormatter = dateFormatter,
            colors = getWantedDatePickerDefaults()
        )
    }
}

@Composable
private fun getWantedDatePickerDefaults() = DatePickerDefaults.colors(
    containerColor = DesignSystemTheme.colors.backgroundNormalNormal,
    titleContentColor = DesignSystemTheme.colors.labelAlternative,
    headlineContentColor = DesignSystemTheme.colors.labelNormal,
    subheadContentColor = DesignSystemTheme.colors.labelNormal,
    weekdayContentColor = DesignSystemTheme.colors.labelNormal,
    navigationContentColor = DesignSystemTheme.colors.labelNormal,
    yearContentColor = DesignSystemTheme.colors.labelNormal,
    disabledYearContentColor = DesignSystemTheme.colors.labelDisable,
    currentYearContentColor = DesignSystemTheme.colors.labelNormal,
    selectedYearContentColor = DesignSystemTheme.colors.staticWhite,
    disabledSelectedYearContentColor = DesignSystemTheme.colors.labelDisable,
    selectedYearContainerColor = DesignSystemTheme.colors.primaryNormal,
    dayContentColor = DesignSystemTheme.colors.labelNormal,
    disabledDayContentColor = DesignSystemTheme.colors.labelDisable,
    selectedDayContentColor = DesignSystemTheme.colors.staticWhite,
    disabledSelectedDayContentColor = DesignSystemTheme.colors.staticWhite,
    selectedDayContainerColor = DesignSystemTheme.colors.primaryNormal,
    disabledSelectedDayContainerColor = DesignSystemTheme.colors.labelDisable,
    todayContentColor = DesignSystemTheme.colors.primaryNormal,
    todayDateBorderColor = DesignSystemTheme.colors.primaryNormal,
    dayInSelectionRangeContentColor = DesignSystemTheme.colors.staticWhite,
    dayInSelectionRangeContainerColor = DesignSystemTheme.colors.primaryNormal,
    dividerColor = DesignSystemTheme.colors.lineNormalNormal,
    dateTextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = DesignSystemTheme.colors.backgroundNormalNormal,
        unfocusedContainerColor = DesignSystemTheme.colors.backgroundNormalNormal,
        errorContainerColor = DesignSystemTheme.colors.interactionDisable,
        focusedIndicatorColor = DesignSystemTheme.colors.primaryNormal,
        focusedLabelColor = DesignSystemTheme.colors.labelAlternative,
        unfocusedLabelColor = DesignSystemTheme.colors.labelAlternative,
    )
)

@DevicePreviews
@Composable
private fun WantedDatePickerPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                DatePicker(state = rememberDatePickerState())
                DatePicker(
                    state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                )
            }
        }
    }
}
