package com.wcabral.feature.store.detail.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wcabral.core.navigation.NavigationDestination
import com.wcabral.feature.store.detail.StoreDetailRoute

object StoreDetailDestination : NavigationDestination {
    const val storeIdArg = "storeId"
    override val route = "store_detail_route/{$storeIdArg}"
    override val destination = "store_detail_destination"

    /**
     * Creates destination route to the store detail
     */
    fun createNavigationRoute(storeId: Int): String {
        val encodedId = Uri.encode(storeId.toString())
        return "store_detail_route/$encodedId"
    }
}

fun NavGraphBuilder.storeDetailGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = StoreDetailDestination.route,
        arguments = listOf(
            navArgument(StoreDetailDestination.storeIdArg) { type = NavType.IntType }
        )
    ) {  backStackEntry ->
        val storeId = requireNotNull(backStackEntry.arguments?.getInt(StoreDetailDestination.storeIdArg)) { "Store id is required as an argument" }
        StoreDetailRoute(
            storeId = storeId,
            onBackClick = onBackClick,
        )
    }
}
