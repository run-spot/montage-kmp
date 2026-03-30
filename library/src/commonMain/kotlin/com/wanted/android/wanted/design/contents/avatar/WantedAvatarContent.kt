package com.wanted.android.wanted.design.contents.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
internal fun WantedAvatarContent(
    modifier: Modifier = Modifier,
    model: Any?,
    placeHolder: DrawableResource? = null,
    isDrawableRes: Boolean = false,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit
) {
    model?.let {
        if ((isDrawableRes && model is DrawableResource) || model is DrawableResource) {
            Image(
                modifier = modifier,
                painter = painterResource(model as DrawableResource),
                contentDescription = "",
                contentScale = contentScale
            )
        } else {
            val placeholderPainter = placeHolder?.let { painterResource(it) }
            AsyncImage(
                modifier = modifier,
                model = model,
                contentDescription = "",
                alignment = alignment,
                contentScale = contentScale,
                placeholder = placeholderPainter,
                error = placeholderPainter,
                fallback = placeholderPainter,
            )
        }
    } ?: run {
        placeHolder?.let {
            Image(
                modifier = modifier,
                painter = painterResource(placeHolder),
                contentDescription = "",
                contentScale = contentScale
            )
        } ?: run {
            Box(
                modifier = modifier
                    .background(DesignSystemTheme.colors.staticWhite)
            )
        }
    }
}
