package com.wanted.android.wanted.design.contents.playtime.playbadge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.contents.playtime.playbadge.WantedPlayBadgeDefaults.Size
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_play
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_28
import com.wanted.android.wanted.design.util.OPACITY_61
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedPlayBadge(
    size: Size = Size.Medium,
    isAlternative: Boolean = false,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .size(size.container)
            .clip(CircleShape)
            .background(DesignSystemTheme.colors.transparent),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(size.container)
                .clip(CircleShape)
                .alpha(if (isAlternative) OPACITY_61 else OPACITY_28)
                .background(
                    if (isAlternative) {
                        DesignSystemTheme.colors.labelNormal.copy(alpha = 0.08f)
                    } else {
                        DesignSystemTheme.colors.labelNormal.copy(alpha = 0.12f)
                    }
                )
        )

        Icon(
            painter = painterResource(Res.drawable.icon_normal_play),
            modifier = Modifier.size(size.icon),
            contentDescription = "",
            tint = DesignSystemTheme.colors.staticWhite
        )
    }
}

@DevicePreviews
@Composable
private fun WantedPlayTimePreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                WantedPlayBadge(
                    modifier = Modifier,
                    size = Size.Small
                )

                WantedPlayBadge(
                    modifier = Modifier,
                    size = Size.Medium
                )

                WantedPlayBadge(
                    modifier = Modifier,
                    size = Size.Large
                )

                WantedPlayBadge(
                    modifier = Modifier,
                    isAlternative = true
                )
            }
        }
    }
}
