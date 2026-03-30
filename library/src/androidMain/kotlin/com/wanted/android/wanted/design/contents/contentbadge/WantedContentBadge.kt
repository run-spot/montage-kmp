@file:JvmName("WantedContentBadgeAndroidKt")

package com.wanted.android.wanted.design.contents.contentbadge

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.wanted.android.designsystem.R
class WantedContentBadge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    lateinit var size: ContentBadgeSize
    var text by mutableStateOf("")
    var type by mutableStateOf(ContentBadgeType.Solid)
    var textColor by mutableStateOf(R.color.label_alternative)
    var backgroundColor by mutableStateOf<Int?>(null)
    var lineColor by mutableStateOf<Int?>(null)
    var leftDrawable by mutableStateOf<Int?>(null)
    var rightDrawable by mutableStateOf<Int?>(null)
    var backgroundAlpha by mutableStateOf(1f)

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.WantedContentBadge).run {
                text = getString(R.styleable.WantedContentBadge_text) ?: ""
                type = ContentBadgeType.entries[getInteger(R.styleable.WantedContentBadge_badge_type, 0)]
                size = ContentBadgeSize.entries[getInteger(R.styleable.WantedContentBadge_badge_size, 0)]
                textColor = getResourceId(R.styleable.WantedContentBadge_textColor, R.color.label_alternative)
                backgroundColor = getResourceId(R.styleable.WantedContentBadge_backgroundColor, 0)
                backgroundAlpha = getFloat(R.styleable.WantedContentBadge_backgroundAlpha, 1f)
                lineColor = getResourceId(R.styleable.WantedContentBadge_lineColor, 0)
                leftDrawable = getResourceId(R.styleable.WantedContentBadge_leftDrawable, 0)
                rightDrawable = getResourceId(R.styleable.WantedContentBadge_rightDrawable, 0)
                recycle()
            }
        }
    }

    @Composable
    override fun Content() {
        WantedContentBadgeOld(
            text = text,
            type = type,
            size = size,
            textColor = colorResource(id = textColor),
            backgroundColor = if (backgroundColor != 0) colorResource(id = backgroundColor!!).copy(alpha = backgroundAlpha) else null,
            lineColor = if (lineColor != 0) colorResource(id = lineColor!!) else null,
            leadingContent = leftDrawable?.takeIf { it != 0 }?.let { drawableRes ->
                {
                    Image(
                        painter = painterResource(id = drawableRes),
                        modifier = Modifier,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(colorResource(id = textColor))
                    )
                }
            },
            trailingContent = rightDrawable?.takeIf { it != 0 }?.let { drawableRes ->
                {
                    Image(
                        painter = painterResource(id = drawableRes),
                        modifier = Modifier,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(colorResource(id = textColor))
                    )
                }
            }
        )
    }
}

@Deprecated("WantedContentBadge 사용")
@Composable
fun WantedContentBadgeOld(
    text: String,
    type: ContentBadgeType = ContentBadgeType.Solid,
    size: ContentBadgeSize = ContentBadgeSize.XSmall,
    textColor: Color,
    backgroundColor: Color? = null,
    lineColor: Color? = null,
    leadingDrawable: Int? = null,
    trailingDrawable: Int? = null,
    leadingContent: @Composable (BoxScope.() -> Unit)? = null,
    trailingContent: @Composable (BoxScope.() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    WantedContentBadgeLegacy(
        text = text,
        type = type,
        size = size,
        textColor = textColor,
        backgroundColor = backgroundColor,
        lineColor = lineColor,
        leadingContent = leadingContent ?: leadingDrawable?.takeIf { it != 0 }?.let { drawableRes ->
            {
                Image(
                    painter = painterResource(id = drawableRes),
                    modifier = Modifier,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(textColor)
                )
            }
        },
        trailingContent = trailingContent ?: trailingDrawable?.takeIf { it != 0 }?.let { drawableRes ->
            {
                Image(
                    painter = painterResource(id = drawableRes),
                    modifier = Modifier,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(textColor)
                )
            }
        },
        onClick = onClick
    )
}
