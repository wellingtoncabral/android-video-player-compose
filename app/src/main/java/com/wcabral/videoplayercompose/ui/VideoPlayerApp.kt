package com.wcabral.videoplayercompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.videoplayercompose.navigation.AppNavHost

@Composable
fun VideoPlayerApp(
    appState: VideoPlayerAppState = rememberVideoPlayerAppState()
) {
    DesignSystemTheme {
        DesignSystemBackground(modifier = Modifier.fillMaxSize()) {
            AppNavHost(
                navController = appState.navController,
                onNavigateToDestination = appState::navigate,
                onBackClick = appState::onBackClick,
                modifier = Modifier
            )
        }
    }
}