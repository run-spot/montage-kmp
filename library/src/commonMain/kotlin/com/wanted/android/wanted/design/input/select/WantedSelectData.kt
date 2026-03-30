package com.wanted.android.wanted.design.input.select

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

/**
 * WantedSelect, WantedMultiSelect에서 사용하는 공통 선택 항목 모델입니다.
 *
 * Android 리소스 Int 대신 multiplatform-safe 타입을 사용해 commonMain에서
 * 상태/모델 계층을 재사용할 수 있게 합니다.
 */
data class WantedSelectData(
    val id: String = "",
    val text: String = "",
    val iconUrl: String = "",
    val any: Any? = null,
    val iconResource: DrawableResource? = null,
    val iconTint: Color? = null,
)
