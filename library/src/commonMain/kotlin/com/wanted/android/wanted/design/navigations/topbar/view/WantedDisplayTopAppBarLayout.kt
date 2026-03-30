package com.wanted.android.wanted.design.navigations.topbar.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.theme.DesignSystemTheme

@Composable
internal fun WantedDisplayTopAppBarLayout(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .height(IntrinsicSize.Min),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            navigationIcon?.let {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    navigationIcon()
                }

                Spacer(modifier = Modifier.weight(1f))
            } ?: kotlin.run {
                title?.let {
                    TopBarTitle(
                        modifier = Modifier.weight(1f),
                        title = title
                    )
                }
            }


            actions?.let {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    actions()
                }
            }
        }

        if (navigationIcon != null && title != null) {
            TopBarTitle(
                modifier = Modifier,
                title = title
            )
        }
    }
}

@Composable
private fun TopBarTitle(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit
) {
    Box(
        modifier.padding(horizontal = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        ProvideTextStyle(
            value = DesignSystemTheme.typography.title3Bold.copy(
                color = DesignSystemTheme.colors.labelStrong
            )
        ) {
            title()
        }
    }
}
