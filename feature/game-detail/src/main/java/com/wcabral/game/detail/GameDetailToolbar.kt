package com.wcabral.game.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemTopAppBar
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun GamesToolbar(onNavigationClick: () -> Unit) {
    DesignSystemTopAppBar(
        titleRes = R.string.empty_string,
        navigationIcon = ImageVector.vectorResource(id = DesignSystemIcons.ArrowBack),
        navigationIconContentDescription = stringResource(id = R.string.back),
        onNavigationClick = onNavigationClick,
        actionIcon = null,
        actionIconContentDescription = null,
    )
}

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode", )
@Composable
fun VideosToolbarPreview() {
    DesignSystemTheme {
        GamesToolbar{}
    }
}
