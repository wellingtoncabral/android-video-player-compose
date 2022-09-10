package com.wcabral.videoplayercompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.videos.videos.VideosDestination
import com.wcabral.feature.videos.videos.videosGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (NavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = VideosDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        videosGraph(onBackClick)
    }
}

