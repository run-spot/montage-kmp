package com.wanted.android.wanted.design.beta

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.profile_default
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_5
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedPersonAvatar(
    modifier: Modifier = Modifier,
    profileImageUrl: String?,
    size: Dp,
    borderColor: Color = DesignSystemTheme.colors.labelNormal.copy(alpha = OPACITY_5),
    alpha: Float = 1f,
) {
    val defaultPainter = painterResource(Res.drawable.profile_default)
    val baseModifier = Modifier
        .size(size)
        .clip(CircleShape)
        .background(color = DesignSystemTheme.colors.backgroundNormalNormal)
        .border(
            width = 1.dp,
            color = borderColor,
            shape = CircleShape
        )

    Box(
        modifier = modifier.then(baseModifier)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .alpha(alpha),
            model = profileImageUrl,
            contentDescription = "Profile Image",
            placeholder = defaultPainter,
            error = defaultPainter,
            fallback = defaultPainter,
            contentScale = ContentScale.Crop,
        )
    }
}
