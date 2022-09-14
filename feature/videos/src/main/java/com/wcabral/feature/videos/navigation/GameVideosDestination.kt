package com.wcabral.feature.videos.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.videos.VideosRoute

object GameVideosDestination : NavigationDestination {
    const val gameIdArg = "gameId"
    override val route = "game_videos_route/{$gameIdArg}"
    override val destination = "game_videos_destination"

    /**
     * Creates destination route to the game videos
     */
    fun createNavigationRoute(gameId: Int): String {
        val encodedId = Uri.encode(gameId.toString())
        return "game_videos_route/$encodedId"
    }
}

fun NavGraphBuilder.gameVideosGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = GameVideosDestination.route,
        arguments = listOf(
            navArgument(GameVideosDestination.gameIdArg) { type = NavType.IntType }
        )
    ) {  backStackEntry ->
        val gameId = requireNotNull(backStackEntry.arguments?.getInt(GameVideosDestination.gameIdArg)) { "Game id is required as an argument" }
        VideosRoute(
            gameId = gameId,
            onBackClick = onBackClick,
        )
    }
}
