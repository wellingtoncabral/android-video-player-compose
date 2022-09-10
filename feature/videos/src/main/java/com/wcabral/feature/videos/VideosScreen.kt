package com.wcabral.feature.videos

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
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.previewGames
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun VideosRoute(
    onBackClick: () -> Unit,
) {
    val viewModel = getViewModel<VideosViewModel>()
    VideosScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is VideosContract.Effect.Navigation.Back) {
                onBackClick()
            }
        },
    )
}

@Composable
fun VideosScreen(
    state: VideosContract.State,
    effectFlow: Flow<VideosContract.Effect>?,
    onEventSent: (event: VideosContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VideosContract.Effect) -> Unit,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackBarMessage = stringResource(R.string.videos_screen_snackbar_loaded_message)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                VideosContract.Effect.DataWasLoaded -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
                VideosContract.Effect.Navigation.Back -> {
                    onNavigationRequested(VideosContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            VideosToolbar(onNavigationClick = { onEventSent(VideosContract.Event.BackButtonClicked) })
        },
    ) {
        Column(modifier = Modifier.padding(horizontal = DesignSystemDimens.ScreenPadding)) {
            when {
                state.isLoading -> VideosLoading()
                state.isError -> VideosError(onRetryClick = { onEventSent(VideosContract.Event.Retry) })
                else -> VideosSuccess(items = state.videos)
            }
        }
    }
}

@Composable
fun VideosSuccess(items: List<Game>) {
    DesignSystemHeader(
        titleRes = R.string.recommended_for_you,
        modifier = Modifier.padding(vertical = DesignSystemDimens.ScreenPadding)
    )
    VideosList(list = items)
}

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun VideosScreenPopulated() {
    DesignSystemTheme {
        VideosScreen(
            state = VideosContract.State(
                videos = previewGames,
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
fun VideosScreenLoading() {
    DesignSystemTheme {
        VideosScreen(
            state = VideosContract.State(
                videos = emptyList(),
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
fun VideosScreenError() {
    DesignSystemTheme {
        VideosScreen(
            state = VideosContract.State(
                videos = emptyList(),
                isLoading = false,
                isError = true
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}