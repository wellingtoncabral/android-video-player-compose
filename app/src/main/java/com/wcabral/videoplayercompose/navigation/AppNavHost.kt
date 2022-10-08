package com.wcabral.videoplayercompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.games.navigation.GamesDestination
import com.wcabral.feature.games.navigation.gamesGraph
import com.wcabral.feature.videos.navigation.GameVideosDestination
import com.wcabral.feature.videos.navigation.gameVideosGraph
import com.wcabral.feature.game.detail.navigation.GameDetailDestination
import com.wcabral.feature.game.detail.navigation.gameDetailGraph
import com.wcabral.feature.store.detail.navigation.StoreDetailDestination
import com.wcabral.feature.store.detail.navigation.storeDetailGraph

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
        gamesGraph(
            navigateToGameDetail = { gameId ->
                onNavigateToDestination(
                    GameDetailDestination, GameDetailDestination.createNavigationRoute(gameId)
                )
            },
            navigateToStoreDetail = { storeId ->
                onNavigateToDestination(
                    StoreDetailDestination, StoreDetailDestination.createNavigationRoute(storeId)
                )
            },
            onBackClick = onBackClick,
        )
        gameDetailGraph(
            navigateToGameVideos = { gameId ->
                onNavigateToDestination(
                    GameVideosDestination, GameVideosDestination.createNavigationRoute(gameId)
                )
            },
            onBackClick = onBackClick,
        )
        gameVideosGraph(onBackClick = onBackClick)
        storeDetailGraph(onBackClick = onBackClick)
    }
}
