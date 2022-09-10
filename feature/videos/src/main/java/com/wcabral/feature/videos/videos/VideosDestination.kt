package com.wcabral.feature.videos.videos

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wcabral.core.navigation.NavigationDestination

object VideosDestination : NavigationDestination {
    override val route = "videos_route"
    override val destination = "videos_destination"
}

fun NavGraphBuilder.videosGraph(
    onBackClick: () -> Unit,
) {
    composable(route = VideosDestination.route) {
        VideosRoute(onBackClick = onBackClick)
    }
}