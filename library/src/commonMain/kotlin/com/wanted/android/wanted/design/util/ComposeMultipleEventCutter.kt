package com.wanted.android.wanted.design.util

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import kotlin.time.TimeMark
import kotlin.time.TimeSource

internal fun Modifier.clickOnce(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = this.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role,
    onClick = { onClick.clickOnce() }
)

internal fun Modifier.clickOnce(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = this.clickable(
    interactionSource = interactionSource,
    indication = indication,
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role,
    onClick = { onClick.clickOnce() }
)

internal fun (() -> Unit).clickOnce(time: Long = 200L) {
    ComposeMultipleEventCutter.processEvent(time) { this() }
}

internal fun <T> ((T) -> Unit).clickOnce(value: T, time: Long = 200L) {
    ComposeMultipleEventCutter.processEvent(time) { this(value) }
}

internal fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

internal object ComposeMultipleEventCutter {
    private var lastEventTime: TimeMark? = null

    fun processEvent(time: Long, event: () -> Unit) {
        val lastEvent = lastEventTime
        if (lastEvent == null || lastEvent.elapsedNow().inWholeMilliseconds >= time) {
            event.invoke()
        }
        lastEventTime = TimeSource.Monotonic.markNow()
    }
}

