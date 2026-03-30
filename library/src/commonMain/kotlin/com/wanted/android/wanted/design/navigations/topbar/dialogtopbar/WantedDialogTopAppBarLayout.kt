package com.wanted.android.wanted.design.navigations.topbar.dialogtopbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
internal fun WantedDialogTopAppBarLayout(
    modifier: Modifier = Modifier,
    variant: WantedDialogTopAppBarContract.Variant = WantedDialogTopAppBarContract.Variant.Emphasized,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .defaultMinSize(
                minHeight =
                    if (variant == WantedDialogTopAppBarContract.Variant.Display) 72.dp else 56.dp
            )
            .padding(vertical = 8.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        navigationIcon?.let { navigationIcon() }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            title?.let {
                ProvideTextStyle(
                    value = if (variant == WantedDialogTopAppBarContract.Variant.Display) {
                        DesignSystemTheme.typography.title3Bold
                    } else {
                        DesignSystemTheme.typography.headline2Bold
                    }.copy(
                        color = DesignSystemTheme.colors.labelStrong
                    )
                ) {
                    title()
                }
            }
        }

        actions?.let {
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                actions()
            }
        }
    }
}
