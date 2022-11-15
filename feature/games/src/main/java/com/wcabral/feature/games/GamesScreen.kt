package com.wcabral.feature.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemErrorPage
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewGames
import com.wcabral.core.model.previewStores
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.core.ui.previews.DevicePreviews
import com.wcabral.core.ui.previews.LightAndNightPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun GamesRoute(
    navigateToGameDetail: (gameId: Int) -> Unit,
    navigateToStoreDetail: (storeId: Int) -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel = getViewModel<GamesViewModel>()
    GamesScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is GamesContract.Effect.Navigation.Back -> onBackClick()
                is GamesContract.Effect.Navigation.ToGameDetail -> navigateToGameDetail(navigationEffect.gameId)
                is GamesContract.Effect.Navigation.ToStoreDetail -> navigateToStoreDetail(navigationEffect.storeId)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    state: GamesContract.State,
    effectFlow: Flow<GamesContract.Effect>?,
    onEventSent: (event: GamesContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: GamesContract.Effect.Navigation) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.games_screen_snackbar_loaded_message)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is GamesContract.Effect.DataWasLoaded -> {
                    snackbarHostState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
                is GamesContract.Effect.Navigation.Back -> {
                    onNavigationRequested(GamesContract.Effect.Navigation.Back)
                }
                is GamesContract.Effect.Navigation.ToGameDetail -> {
                    onNavigationRequested(GamesContract.Effect.Navigation.ToGameDetail(effect.gameId))
                }
                is GamesContract.Effect.Navigation.ToStoreDetail -> {
                    onNavigationRequested(GamesContract.Effect.Navigation.ToStoreDetail(effect.storeId))
                }
            }
        }?.collect()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            GamesToolbar(
                onNavigationClick = { onEventSent(GamesContract.Event.BackButtonClicked) },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when {
                state.isLoading -> DesignSystemLoading(modifier = Modifier.fillMaxSize())
                state.isError -> DesignSystemErrorPage(
                    titleRes = R.string.generic_error_title,
                    descriptionRes = R.string.generic_error_description,
                    buttonTitleRes = R.string.generic_error_button_title,
                ) {
                    onEventSent(GamesContract.Event.Retry)
                }
                else -> GamesNestedList(
                    list = state.games,
                    stores = state.stores,
                    onGameClick = { game -> onEventSent(GamesContract.Event.GameSelection(game.id)) },
                    onStoreClick = {
                        store -> onEventSent(GamesContract.Event.StoreSelection(store.id))
                    }
                )
            }
        }
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun GamesScreenPopulated() {
    DesignSystemTheme {
        GamesScreen(
            state = GamesContract.State(
                games = previewGames,
                stores = previewStores,
                isLoading = false,
                isError = false,
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun GamesScreenLoading() {
    DesignSystemTheme {
        GamesScreen(
            state = GamesContract.State(
                games = emptyList(),
                stores = emptyList(),
                isLoading = true,
                isError = false
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun GamesScreenError() {
    DesignSystemTheme {
        GamesScreen(
            state = GamesContract.State(
                games = emptyList(),
                stores = emptyList(),
                isLoading = false,
                isError = true
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}
