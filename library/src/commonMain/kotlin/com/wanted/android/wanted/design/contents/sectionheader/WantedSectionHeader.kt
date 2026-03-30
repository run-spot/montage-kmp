package com.wanted.android.wanted.design.contents.sectionheader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.contents.sectionheader.WantedSectionHeaderDefaults.Size
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedSectionHeader(
    title: String,
    modifier: Modifier,
    size: Size = Size.Medium,
    textStyle: TextStyle? = null,
    headingContents: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null
) {
    val density = LocalDensity.current
    val style = textStyle ?: when (size) {
        Size.Large -> DesignSystemTheme.typography.title3Bold
        Size.Medium -> DesignSystemTheme.typography.heading2Bold
        Size.Small -> DesignSystemTheme.typography.headline2Bold
        Size.XSmall -> DesignSystemTheme.typography.label1Bold
    }.copy(
        color = if (size == Size.XSmall) {
            DesignSystemTheme.colors.labelAlternative
        } else {
            DesignSystemTheme.colors.labelStrong
        }
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f, false),
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (headingContents != null) 1 else Int.MAX_VALUE,
                style = style
            )

            headingContents?.let {
                Box(
                    modifier = Modifier.height(with(density) { style.lineHeight.toDp() }),
                    contentAlignment = Alignment.BottomStart
                ) {
                    headingContents()
                }
            }
        }

        trailingContent?.let {
            Row(
                modifier = Modifier.height(with(density) { style.lineHeight.toDp() }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                trailingContent.invoke(this)
            }
        }
    }
}

@DevicePreviews
@Composable
private fun WantedSectionHeaderPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목",
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    },
                    trailingContent = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(modifier = Modifier, title = "제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목")

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    },
                    trailingContent = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목",
                    trailingContent = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    trailingContent = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    size = Size.XSmall,
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    size = Size.Small,
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    size = Size.Medium,
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )

                WantedSectionHeader(
                    modifier = Modifier,
                    title = "제목",
                    size = Size.Large,
                    headingContents = {
                        Icon(
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}
