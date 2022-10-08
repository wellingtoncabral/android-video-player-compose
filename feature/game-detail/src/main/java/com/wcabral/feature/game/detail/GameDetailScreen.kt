package com.wcabral.feature.game.detail

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemErrorPage
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewGameDetail
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.core.ui.previews.DevicePreviews
import com.wcabral.core.ui.previews.LightAndNightPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameDetailRoute(
    gameId: Int,
    navigateToGameVideos: (gameId: Int) -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel = getViewModel<GameDetailViewModel> { parametersOf(gameId) }
    GameDetailScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is GameDetailContract.Effect.Navigation.Back -> onBackClick()
                is GameDetailContract.Effect.Navigation.ToGameVideos -> navigateToGameVideos(navigationEffect.gameId)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    state: GameDetailContract.State,
    effectFlow: Flow<GameDetailContract.Effect>?,
    onEventSent: (event: GameDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: GameDetailContract.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is GameDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(GameDetailContract.Effect.Navigation.Back)
                }
                is GameDetailContract.Effect.Navigation.ToGameVideos -> {
                    onNavigationRequested(GameDetailContract.Effect.Navigation.ToGameVideos(effect.gameId))
                }
            }
        }?.collect()
    }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarScrollState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GameDetailToolbar(
                scrollBehavior = scrollBehavior,
                onNavigationClick = { onEventSent(GameDetailContract.Event.BackButtonClicked) }
            )
        },
    ) {
        Column {
            when {
                state.isLoading -> DesignSystemLoading(modifier = Modifier.fillMaxSize())
                state.isError -> DesignSystemErrorPage(
                    titleRes = R.string.generic_error_title,
                    descriptionRes = R.string.generic_error_description,
                    buttonTitleRes = R.string.generic_error_button_title,
                ) {
                    onEventSent(GameDetailContract.Event.Retry)
                }
                state.gameDetail != null -> GameDetailView(
                    gameDetail = state.gameDetail,
                    onNavigateToGameVideos = { gameId -> onEventSent(GameDetailContract.Event.VideosClicked(gameId)) }
                )
            }
        }
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun GameDetailScreenPopulated() {
    DesignSystemTheme {
        GameDetailScreen(
            state = GameDetailContract.State(
                gameDetail = previewGameDetail,
                isLoading = false,
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
fun GameDetailScreenLoading() {
    DesignSystemTheme {
        GameDetailScreen(
            state = GameDetailContract.State(
                gameDetail = null,
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
fun GameDetailScreenError() {
    DesignSystemTheme {
        GameDetailScreen(
            state = GameDetailContract.State(
                gameDetail = null,
                isLoading = false,
                isError = true
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}
