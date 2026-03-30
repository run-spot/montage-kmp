package com.wanted.android.wanted.design.contents.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.contents.accordion.WantedAccordionDefaults.VerticalPadding
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_chevron_down
import com.wanted.android.wanted.design.resources.icon_normal_chevron_up
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.clickOnce
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun WantedAccordionHeader(
    title: String,
    verticalPadding: VerticalPadding,
    style: TextStyle,
    fillWidth: Boolean,
    maxLine: Int = Int.MAX_VALUE,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickOnce(onClick = onClick)
            .wrapContentSize()
            .padding(vertical = verticalPadding.value)
            .padding(horizontal = if (fillWidth) 20.dp else 0.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        leadingIcon?.let {
            Box(
                modifier = Modifier
                    .size(with(LocalDensity.current) { style.lineHeight.toDp() })
                    .widthIn(max = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                leadingIcon()
            }
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = style,
            maxLines = maxLine,
            overflow = TextOverflow.Ellipsis
        )

        Box(
            modifier = Modifier
                .height(with(LocalDensity.current) { style.lineHeight.toDp() })
                .widthIn(max = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            trailIcon()
        }
    }
}

@DevicePreviews
@Composable
private fun AccordionHeaderPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                WantedAccordionHeader(
                    modifier = Modifier.fillMaxWidth(),
                    verticalPadding = VerticalPadding.Padding12,
                    title = "제목",
                    style = DesignSystemTheme.typography.body2Bold.copy(
                        color = DesignSystemTheme.colors.labelNormal
                    ),
                    fillWidth = false,
                    leadingIcon = {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color.Blue)
                        )
                    },
                    trailIcon = {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = if (true) {
                                painterResource(Res.drawable.icon_normal_chevron_up)
                            } else {
                                painterResource(Res.drawable.icon_normal_chevron_down)
                            },
                            tint = DesignSystemTheme.colors.labelNormal,
                            contentDescription = ""
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
}
