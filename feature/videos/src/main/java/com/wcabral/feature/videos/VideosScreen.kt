package com.wcabral.feature.videos

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.ErrorPage
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewVideos
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideosScreenPopulated() {
    DesignSystemTheme {
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideosScreenLoading() {
    DesignSystemTheme {
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GamesScreenError() {
    DesignSystemTheme {
        VideosScreen(
            state = VideosContract.State(
                mediaPlayer = null,
                videos = emptyList(),
                currentMediaId = null,
                isLoading = true,
                isError = true,
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}
