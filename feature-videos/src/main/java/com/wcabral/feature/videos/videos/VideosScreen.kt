package com.wcabral.feature.videos.videos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.model.Game
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.feature.videos.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun VideosScreen(
    state: VideosContract.State,
    effectFlow: Flow<VideosContract.Effect>?,
    onEventSent: (event: VideosContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VideosContract.Effect.DataWasLoaded) -> Unit,
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
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBar() }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when {
                state.isLoading -> Text(text = "Loading")
                state.isError -> Text(text = "Error")
                else -> {
                    state.videos.forEach {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Composable
fun topBar() {
    TopAppBar(title = { Text(text = "Videos") })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val videos = List(3) { Game(it, "Game $it", backgroundImage = null) }
    VideosScreen(
        state = VideosContract.State(
            videos = videos,
            isLoading = false,
            isError = false,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}