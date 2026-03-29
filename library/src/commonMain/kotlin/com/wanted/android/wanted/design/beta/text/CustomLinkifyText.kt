package com.wanted.android.wanted.design.beta.text

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
fun CustomLinkifyText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    linkStyle: SpanStyle = SpanStyle(
        color = DesignSystemTheme.colors.primaryNormal,
        textDecoration = TextDecoration.Underline
    ),
    onClickLink: ((url: String) -> Unit)? = null
) {
    val uriHandler = LocalUriHandler.current

    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val linksList = extractUrls(text)
    val annotatedString = buildAnnotatedString {

        append(text)

        linksList.forEach {
            addStyle(
                style = linkStyle,
                start = it.start,
                end = it.end
            )

            addStringAnnotation(
                tag = URL,
                annotation = it.url,
                start = it.start,
                end = it.end
            )
        }
    }

    Text(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offsetPosition ->
                    layoutResult.value?.let {
                        val position = it.getOffsetForPosition(offsetPosition)
                        annotatedString.getStringAnnotations(position, position)
                            .firstOrNull()
                            ?.let { result ->
                                if (result.tag == URL) {
                                    onClickLink?.let {
                                        onClickLink(result.item)
                                    } ?: kotlin.run {
                                        uriHandler.openUri(result.item)
                                    }
                                }
                            }
                    }
                }
            },
        text = annotatedString,
        onTextLayout = { layoutResult.value = it },
        style = style
    )
}

private fun extractUrls(text: String): List<LinkInfos> {
    return urlPattern.findAll(text).map { match ->
        val urlText = match.groups[1]?.value ?: match.value
        val start = match.range.first + match.value.indexOf(urlText)
        val end = start + urlText.length
        var url = urlText
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "https://$url"

        LinkInfos(
            url = url,
            start = start,
            end = end
        )
    }.toList()
}

private data class LinkInfos(
    val url: String,
    val start: Int,
    val end: Int
)

private const val URL = "URL"
private val urlPattern = Regex(
    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
    setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE)
)
