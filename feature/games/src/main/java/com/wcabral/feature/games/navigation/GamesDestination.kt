package com.wcabral.feature.games.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.games.GamesRoute

object GamesDestination : NavigationDestination {
    override val route = "games_route"
    override val destination = "games_destination"
}

fun NavGraphBuilder.gamesGraph(
    navigateToGameDetail: (gameId: Int) -> Unit,
    navigateToStoreDetail: (storeId: Int) -> Unit,
    onBackClick: () -> Unit,
) {
    composable(route = GamesDestination.route) {
        GamesRoute(
            navigateToGameDetail = navigateToGameDetail,
            navigateToStoreDetail = navigateToStoreDetail,
            onBackClick = onBackClick
        )
    }
}