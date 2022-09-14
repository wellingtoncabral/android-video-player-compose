package com.wcabral.feature.games

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.ErrorPage
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewGames
import com.wcabral.core.model.previewStores
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun GamesRoute(
    navigateToGameVideos: (Int) -> Unit,
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
                is GamesContract.Effect.Navigation.ToGameVideos -> navigateToGameVideos(navigationEffect.gameId)
            }
        },
    )
}

@Composable
fun GamesScreen(
    state: GamesContract.State,
    effectFlow: Flow<GamesContract.Effect>?,
    onEventSent: (event: GamesContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: GamesContract.Effect.Navigation) -> Unit,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackBarMessage = stringResource(R.string.games_screen_snackbar_loaded_message)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is GamesContract.Effect.DataWasLoaded -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
                is GamesContract.Effect.Navigation.Back -> {
                    onNavigationRequested(GamesContract.Effect.Navigation.Back)
                }
                is GamesContract.Effect.Navigation.ToGameVideos -> {
                    onNavigationRequested(GamesContract.Effect.Navigation.ToGameVideos(effect.gameId))
                }
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            GamesToolbar(onNavigationClick = { onEventSent(GamesContract.Event.BackButtonClicked) })
        },
    ) {
        Column(modifier = Modifier.padding(horizontal = DesignSystemDimens.Padding.ScreenHorizontal)) {
            when {
                state.isLoading -> DesignSystemLoading()
                state.isError -> ErrorPage(
                    titleRes = R.string.generic_error_title,
                    descriptionRes = R.string.generic_error_description,
                    buttonTitleRes = R.string.generic_error_button_title,
                ) {
                    onEventSent(GamesContract.Event.Retry)
                }
                else -> GamesNestedList(
                    list = state.games,
                    stores = state.stores,
                    onGameClick = { game -> onEventSent(GamesContract.Event.GameSelection(game.id)) }
                )
            }
        }
    }
}

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
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