package com.wanted.android.wanted.design.contents.contentbadge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_12
import com.wanted.android.wanted.design.util.clickOnce
import com.wanted.android.wanted.design.util.wantedRippleEffect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedContentBadge(
    text: String,
    modifier: Modifier = Modifier,
    type: ContentBadgeType = ContentBadgeType.Solid,
    size: ContentBadgeSize = ContentBadgeSize.Small,
    color: ContentBadgeColor = ContentBadgeColor.Neutral,
    accentDefault: WantedContentBadgeDefault = if (color == ContentBadgeColor.Accent) {
        WantedContentBadgeDefaults.getAccentDefault()
    } else {
        WantedContentBadgeDefaults.getNeutralDefault()
    },
    leadingIconResource: DrawableResource? = null,
    trailingIconResource: DrawableResource? = null,
    onClick: (() -> Unit)? = null,
) {
    WantedContentBadgeWithSlots(
        text = text,
        modifier = modifier,
        type = type,
        size = size,
        color = color,
        accentDefault = accentDefault,
        leadingContent = leadingIconResource?.let { resource ->
            {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(resource),
                    contentDescription = null,
                    tint = getContentColor(accentDefault)
                )
            }
        },
        trailingContent = trailingIconResource?.let { resource ->
            {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(resource),
                    contentDescription = null,
                    tint = getContentColor(accentDefault)
                )
            }
        },
        onClick = onClick
    )
}

@Composable
internal fun WantedContentBadgeWithSlots(
    text: String,
    modifier: Modifier = Modifier,
    type: ContentBadgeType = ContentBadgeType.Solid,
    size: ContentBadgeSize = ContentBadgeSize.Small,
    color: ContentBadgeColor = ContentBadgeColor.Neutral,
    accentDefault: WantedContentBadgeDefault = if (color == ContentBadgeColor.Accent) {
        WantedContentBadgeDefaults.getAccentDefault()
    } else {
        WantedContentBadgeDefaults.getNeutralDefault()
    },
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    WantedContentBadgeLayout(
        modifier = modifier
            .clip(RoundedCornerShape(getRadius(size = size)))
            .background(getBackground(type = type, default = accentDefault))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(getRadius(size = size)),
                color = getOutlineColor(type = type, default = accentDefault)
            )
            .clickOnce(
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                indication = if (color == ContentBadgeColor.Neutral) {
                    wantedRippleEffect(DesignSystemTheme.colors.labelNormal.copy(OPACITY_12))
                } else {
                    wantedRippleEffect(accentDefault.backgroundColor.copy(OPACITY_12))
                },
                enabled = onClick != null
            ) { onClick?.invoke() },
        size = size,
        text = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = getContentColor(default = accentDefault)
            )
        },
        leadingContent = leadingContent,
        trailingContent = trailingContent
    )
}

@Composable
internal fun WantedContentBadgeLegacy(
    text: String,
    type: ContentBadgeType = ContentBadgeType.Solid,
    size: ContentBadgeSize = ContentBadgeSize.XSmall,
    textColor: Color,
    backgroundColor: Color? = null,
    lineColor: Color? = null,
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val roundedCornerShape = RoundedCornerShape(getRadius(size))

    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(roundedCornerShape)
            .then(if (onClick != null) Modifier.clickOnce { onClick() } else Modifier)
            .then(
                if (type == ContentBadgeType.Solid) {
                    backgroundColor?.let {
                        Modifier.background(color = it, shape = roundedCornerShape)
                    } ?: Modifier
                } else {
                    lineColor?.let {
                        Modifier.border(BorderStroke(1.dp, it), roundedCornerShape)
                    } ?: Modifier
                }
            )
            .padding(horizontal = getPadding(size).first, vertical = getPadding(size).second),
        horizontalArrangement = Arrangement.spacedBy(
            space = when (size) {
                ContentBadgeSize.Large -> 4.dp
                ContentBadgeSize.Small -> 3.dp
                ContentBadgeSize.XSmall -> 2.dp
            }
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingContent?.let {
            Box(modifier = Modifier.getContentBadgeDrawableSize(size)) {
                it()
            }
        }
        Text(
            text = text,
            modifier = Modifier.wrapContentSize(),
            style = getContentBadgeTypography(size),
            color = textColor.copy(alpha = 1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        trailingContent?.let {
            Box(modifier = Modifier.getContentBadgeDrawableSize(size)) {
                it()
            }
        }
    }
}

@Composable
private fun WantedContentBadgeLayout(
    size: ContentBadgeSize,
    modifier: Modifier = Modifier,
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    text: @Composable () -> Unit,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(getRadius(size)))
            .padding(horizontal = getHorizontalPadding(size))
            .padding(vertical = getVerticalPadding(size)),
        horizontalArrangement = Arrangement.spacedBy(space = getHorizontalAlimentSpace(size)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingContent?.let {
            Box(modifier = Modifier.size(getIconSize(size))) {
                leadingContent()
            }
        }

        ProvideTextStyle(value = getContentBadgeTypography(size = size)) {
            text()
        }

        trailingContent?.let {
            Box(modifier = Modifier.size(getIconSize(size))) {
                trailingContent()
            }
        }
    }
}

private fun getRadius(size: ContentBadgeSize) = when (size) {
    ContentBadgeSize.Large -> 8.dp
    ContentBadgeSize.Small -> 6.dp
    ContentBadgeSize.XSmall -> 6.dp
}

private fun getHorizontalPadding(size: ContentBadgeSize) = when (size) {
    ContentBadgeSize.XSmall -> 6.dp
    ContentBadgeSize.Small -> 6.dp
    ContentBadgeSize.Large -> 8.dp
}

private fun getVerticalPadding(size: ContentBadgeSize) = when (size) {
    ContentBadgeSize.XSmall -> 3.dp
    ContentBadgeSize.Small -> 4.dp
    ContentBadgeSize.Large -> 7.dp
}

private fun getHorizontalAlimentSpace(size: ContentBadgeSize) = when (size) {
    ContentBadgeSize.XSmall -> 1.dp
    ContentBadgeSize.Small -> 3.dp
    ContentBadgeSize.Large -> 4.dp
}

private fun getIconSize(size: ContentBadgeSize) = when (size) {
    ContentBadgeSize.Large -> 16.dp
    ContentBadgeSize.Small -> 13.dp
    ContentBadgeSize.XSmall -> 12.dp
}

@Composable
private fun getBackground(type: ContentBadgeType, default: WantedContentBadgeDefault): Color {
    return if (type == ContentBadgeType.Solid) default.backgroundColor else DesignSystemTheme.colors.transparent
}

@Composable
private fun getOutlineColor(type: ContentBadgeType, default: WantedContentBadgeDefault): Color {
    return if (type == ContentBadgeType.Solid) DesignSystemTheme.colors.transparent else default.outLineColor
}

private fun getContentColor(default: WantedContentBadgeDefault): Color = default.contentColor

private fun getPadding(size: ContentBadgeSize): Pair<Dp, Dp> =
    when (size) {
        ContentBadgeSize.XSmall -> Pair(4.dp, 3.dp)
        ContentBadgeSize.Small -> Pair(8.dp, 4.dp)
        ContentBadgeSize.Large -> Pair(12.dp, 6.dp)
    }

@Composable
private fun getContentBadgeTypography(size: ContentBadgeSize): TextStyle = when (size) {
    ContentBadgeSize.Large -> DesignSystemTheme.typography.label2Medium
    ContentBadgeSize.Small -> DesignSystemTheme.typography.caption1Medium
    ContentBadgeSize.XSmall -> DesignSystemTheme.typography.caption2Medium
}

private fun Modifier.getContentBadgeDrawableSize(size: ContentBadgeSize): Modifier = this.then(
    when (size) {
        ContentBadgeSize.Large -> Modifier.height(16.dp).wrapContentWidth()
        ContentBadgeSize.Small -> Modifier.height(14.dp).wrapContentWidth()
        ContentBadgeSize.XSmall -> Modifier.size(12.dp).wrapContentWidth()
    }
)

enum class ContentBadgeSize {
    XSmall, Small, Large
}

enum class ContentBadgeType {
    Solid, Outlined
}

enum class ContentBadgeColor {
    Neutral, Accent
}
