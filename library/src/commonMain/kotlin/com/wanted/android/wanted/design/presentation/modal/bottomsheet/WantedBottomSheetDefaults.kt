package com.wanted.android.wanted.design.presentation.modal.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme

object WantedBottomSheetDefaults {
    @Composable
    fun DragHandle(
        modifier: Modifier = Modifier,
        color: Color = DesignSystemTheme.colors.backgroundElevatedNormal,
        shape: Shape = MaterialTheme.shapes.extraLarge,
    ) {
        Surface(
            modifier = modifier.padding(top = 7.dp),
            color = color,
            contentColor = color,
            shape = shape
        ) {
            Box(
                Modifier
                    .size(width = 40.dp, height = 5.dp)
                    .clip(RoundedCornerShape(1000.dp))
                    .background(
                        DesignSystemTheme.colors.fillStrong,
                        RoundedCornerShape(1000.dp)
                    )
            )
        }
    }
}
