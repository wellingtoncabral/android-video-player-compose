package com.wcabral.videoplayercompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wcabral.core.navigation.NavigationDestination

@Composable
fun rememberVideoPlayerAppState(
    navController: NavHostController = rememberNavController()
): VideoPlayerAppState {
    return remember(navController) {
        VideoPlayerAppState(navController)
    }
}

@Stable
class VideoPlayerAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigate(destination: NavigationDestination, route: String? = null) {
        navController.navigate(route ?: destination.route)
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}