package com.wcabral.feature.videos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.ErrorPage
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewVideos
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.core.ui.previews.DevicePreviews
import com.wcabral.core.ui.previews.LightAndNightPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun VideosRoute(
    gameId: Int,
    onBackClick: () -> Unit
) {
    val viewModel = getViewModel<VideosViewModel> { parametersOf(gameId) }
    VideosScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is VideosContract.Effect.Navigation.Back -> onBackClick()
            }
        },
    )
}

@Composable
fun VideosScreen(
    state: VideosContract.State,
    effectFlow: Flow<VideosContract.Effect>?,
    onEventSent: (event: VideosContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VideosContract.Effect.Navigation) -> Unit,
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                VideosContract.Effect.Navigation.Back -> {
                    onNavigationRequested(VideosContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    when {
        state.isLoading -> DesignSystemLoading(modifier = Modifier.fillMaxSize())
        state.isError -> ErrorPage(
            titleRes = R.string.generic_error_title,
            descriptionRes = R.string.generic_error_description,
            buttonTitleRes = R.string.generic_error_button_title,
            onRetryClick = { onEventSent(VideosContract.Event.Retry) }
        )
        else -> VideosView(
            mediaPlayer = state.mediaPlayer,
            currentMediaId = state.currentMediaId,
            videos = state.videos,
            onBackClick = { onEventSent(VideosContract.Event.BackButtonClicked) }
        )
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun VideosScreenPopulated() {
    DesignSystemTheme {
        DesignSystemBackground {
            VideosScreen(
                state = VideosContract.State(
                    mediaPlayer = null,
                    videos = previewVideos,
                    currentMediaId = "",
                    isLoading = false,
                    isError = false,
                ),
                effectFlow = null,
                onEventSent = {},
                onNavigationRequested = {},
            )
        }
    }
}

@LightAndNightPreviews
@Composable
fun VideosScreenLoading() {
    DesignSystemTheme {
        DesignSystemBackground {
            VideosScreen(
                state = VideosContract.State(
                    mediaPlayer = null,
                    videos = emptyList(),
                    currentMediaId = null,
                    isLoading = true,
                    isError = false,
                ),
                effectFlow = null,
                onEventSent = {},
                onNavigationRequested = {},
            )
        }
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun GamesScreenError() {
    DesignSystemTheme {
        DesignSystemBackground {
            VideosScreen(
                state = VideosContract.State(
                    mediaPlayer = null,
                    videos = emptyList(),
                    currentMediaId = null,
                    isLoading = false,
                    isError = true,
                ),
                effectFlow = null,
                onEventSent = {},
                onNavigationRequested = {},
            )
        }
    }
}
