package com.wanted.android.wanted.design.loading.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter

/**
 * WantedLogoProgressIndicator
 *
 * Wanted 심볼 Lottie 애니메이션을 표시하는 컴포넌트입니다.
 *
 * 시스템 다크 모드 설정에 따라 자동으로 라이트/다크 테마의 로딩 애니메이션이 표시됩니다.
 *
 * @param modifier Modifier: 컴포넌트에 적용할 Modifier입니다.
 */
@Composable
fun WantedLogoProgressIndicator(modifier: Modifier = Modifier) {
    val animationFile = if (isSystemInDarkTheme()) {
        "loading-wanted-dark.json"
    } else {
        "loading-wanted-light.json"
    }
    val composition by rememberLottieComposition {
        val animationBytes = Res.readBytes("files/$animationFile")
        LottieCompositionSpec.JsonString(animationBytes.decodeToString())
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Compottie.IterateForever
    )

    Image(
        modifier = modifier.defaultMinSize(32.dp),
        painter = rememberLottiePainter(
            composition = composition,
            progress = { progress }
        ),
        contentDescription = ""
    )
}
