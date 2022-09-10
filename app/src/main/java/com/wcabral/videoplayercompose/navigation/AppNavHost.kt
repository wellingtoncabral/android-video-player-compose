package com.wcabral.videoplayercompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.games.navigation.GamesDestination
import com.wcabral.feature.games.navigation.gamesGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (NavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = GamesDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        gamesGraph(onBackClick)
    }
}

