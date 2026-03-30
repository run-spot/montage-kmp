package com.wanted.android.wanted.design.feedback.fallback

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.actions.button.WantedButton
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_refresh
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.ButtonSize
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant
import com.wanted.android.wanted.design.util.DevicePreviews
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedFallbackView(
    modifier: Modifier = Modifier,
    buttonVariant: WantedFallbackButtonVariant = WantedFallbackButtonVariant.Single,
    heading: String? = null,
    description: String? = null,
    positive: String? = null,
    negative: String? = null,
    positiveColor: ButtonType = ButtonType.ASSISTIVE,
    negativeColor: ButtonType = ButtonType.ASSISTIVE,
    image: @Composable (() -> Unit)? = null,
    onClickPositive: () -> Unit = {},
    onClickNegative: () -> Unit = {}
) {
    WantedFallbackLayout(
        modifier = modifier.fillMaxWidth(),
        image = image,
        heading = heading?.let {
            {
                Text(
                    text = it,
                    textAlign = TextAlign.Center
                )
            }
        },
        description = description?.let {
            {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        button = {
            when (buttonVariant) {
                WantedFallbackButtonVariant.Single -> {
                    WantedFallbackSingleButton(
                        positive = positive,
                        positiveColor = positiveColor,
                        onClickPositive = onClickPositive
                    )
                }

                WantedFallbackButtonVariant.Horizontal -> {
                    WantedFallbackHorizontalButtons(
                        positive = positive,
                        negative = negative,
                        positiveColor = positiveColor,
                        negativeColor = negativeColor,
                        onClickPositive = onClickPositive,
                        onClickNegative = onClickNegative
                    )
                }

                WantedFallbackButtonVariant.Vertical -> {
                    WantedFallbackVerticalButtons(
                        positive = positive,
                        negative = negative,
                        positiveColor = positiveColor,
                        negativeColor = negativeColor,
                        onClickPositive = onClickPositive,
                        onClickNegative = onClickNegative
                    )
                }
            }
        }
    )
}

@Composable
private fun WantedFallbackSingleButton(
    positive: String?,
    positiveColor: ButtonType,
    modifier: Modifier = Modifier,
    onClickPositive: () -> Unit
) {
    positive?.let {
        Box(modifier = modifier) {
            WantedButton(
                text = it,
                variant = ButtonVariant.OUTLINED,
                type = positiveColor,
                size = ButtonSize.SMALL,
                onClick = onClickPositive
            )
        }
    }
}

@Composable
private fun WantedFallbackHorizontalButtons(
    positive: String?,
    negative: String?,
    positiveColor: ButtonType,
    negativeColor: ButtonType,
    modifier: Modifier = Modifier,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit
) {
    if (positive != null || negative != null) {
        Row(
            modifier = modifier.width(intrinsicSize = IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            negative?.let {
                WantedButton(
                    modifier = Modifier.weight(1f),
                    text = it,
                    variant = ButtonVariant.OUTLINED,
                    type = negativeColor,
                    size = ButtonSize.SMALL,
                    onClick = onClickNegative
                )
            }

            positive?.let {
                WantedButton(
                    modifier = Modifier.weight(1f),
                    text = it,
                    variant = ButtonVariant.OUTLINED,
                    type = positiveColor,
                    size = ButtonSize.SMALL,
                    onClick = onClickPositive
                )
            }
        }
    }
}

@Composable
private fun WantedFallbackVerticalButtons(
    positive: String?,
    negative: String?,
    positiveColor: ButtonType,
    negativeColor: ButtonType,
    modifier: Modifier = Modifier,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit
) {
    if (positive != null || negative != null) {
        Column(
            modifier = modifier.width(intrinsicSize = IntrinsicSize.Max),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            positive?.let {
                WantedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = it,
                    variant = ButtonVariant.OUTLINED,
                    type = positiveColor,
                    size = ButtonSize.SMALL,
                    onClick = onClickPositive
                )
            }

            negative?.let {
                WantedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = it,
                    variant = ButtonVariant.OUTLINED,
                    type = negativeColor,
                    size = ButtonSize.SMALL,
                    onClick = onClickNegative
                )
            }
        }
    }
}

@Composable
private fun WantedFallbackLayout(
    modifier: Modifier,
    image: @Composable (() -> Unit)? = null,
    heading: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    button: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.padding(bottom = image?.let { 20.dp } ?: 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        image?.let {
            Box(
                modifier = Modifier.size(160.dp),
                contentAlignment = Alignment.Center
            ) {
                image()
            }
        }

        WantedFallbackLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = image?.let { 0.dp } ?: 4.dp)
                .wrapContentHeight(),
            heading = heading,
            description = description,
            button = button
        )
    }
}

@Composable
private fun WantedFallbackLayout(
    modifier: Modifier,
    heading: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    button: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (heading != null || description != null) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                heading?.let {
                    ProvideTextStyle(
                        value = DesignSystemTheme.typography.heading2Bold.copy(
                            color = DesignSystemTheme.colors.labelNormal
                        )
                    ) {
                        heading()
                    }
                }

                description?.let {
                    ProvideTextStyle(
                        value = DesignSystemTheme.typography.body1ReadingRegular.copy(
                            color = DesignSystemTheme.colors.labelAlternative
                        )
                    ) {
                        description()
                    }
                }
            }
        }

        button?.invoke()
    }
}

enum class WantedFallbackButtonVariant {
    Single,
    Horizontal,
    Vertical
}

@DevicePreviews
@Composable
private fun WantedFallbackViewPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                WantedFallbackView(
                    heading = "타이틀이 들어가요.",
                    description = "상황에 대한 설명이 들어가요.\n설명은 최대 두 줄로 작성해요.",
                    positive = "텍스트",
                    onClickPositive = {}
                )

                WantedFallbackView(
                    image = {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(DesignSystemTheme.colors.labelDisable)
                                .padding(10.dp),
                            painter = painterResource(Res.drawable.icon_normal_refresh),
                            contentScale = ContentScale.Crop,
                            contentDescription = ""
                        )
                    },
                    heading = "타이틀이 들어가요.",
                    description = "상황에 대한 설명이 들어가요.\n설명은 최대 두 줄로 작성해요.",
                    positive = "텍스트",
                    onClickPositive = {}
                )
            }
        }
    }
}

