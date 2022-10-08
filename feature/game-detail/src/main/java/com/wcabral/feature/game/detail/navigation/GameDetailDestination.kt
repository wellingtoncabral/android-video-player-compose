package com.wcabral.feature.game.detail.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.game.detail.GameDetailRoute

object GameDetailDestination : NavigationDestination {
    const val gameIdArg = "gameId"
    override val route = "game_detail_route/{$gameIdArg}"
    override val destination = "game_detail_destination"

    /**
     * Creates destination route to the game videos
     */
    fun createNavigationRoute(gameId: Int): String {
        val encodedId = Uri.encode(gameId.toString())
        return "game_detail_route/$encodedId"
    }
}

fun NavGraphBuilder.gameDetailGraph(
    navigateToGameVideos: (gameId: Int) -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = GameDetailDestination.route,
        arguments = listOf(
            navArgument(GameDetailDestination.gameIdArg) { type = NavType.IntType }
        )
    ) {  backStackEntry ->
        val gameId = requireNotNull(backStackEntry.arguments?.getInt(GameDetailDestination.gameIdArg)) { "Game id is required as an argument" }
        GameDetailRoute(
            gameId = gameId,
            navigateToGameVideos = navigateToGameVideos,
            onBackClick = onBackClick,
        )
    }
}
