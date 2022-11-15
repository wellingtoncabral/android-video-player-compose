@file:OptIn(ExperimentalMaterial3Api::class)

package com.wcabral.feature.games

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.wcabral.core.designsystem.component.DesignSystemTopAppBar
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun GamesToolbar(onNavigationClick: () -> Unit) {
    DesignSystemTopAppBar(
        titleRes = R.string.app_name,
        navigationIcon = DesignSystemIcons.AppIcon,
        navigationIconContentDescription = stringResource(id = R.string.back),
        onNavigationClick = onNavigationClick,
        actionIcon = ImageVector.vectorResource(id = DesignSystemIcons.Search),
        actionIconContentDescription = stringResource(id = R.string.search),
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        )
    )
}

@LightAndNightPreviews
@Composable
fun VideosToolbarPreview() {
    DesignSystemTheme {
        GamesToolbar{}
    }
}
