package com.wcabral.feature.videos.videos

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemTopAppBar
import com.wcabral.core.designsystem.dimen.Dimens
import com.wcabral.core.designsystem.icon.Icons
import com.wcabral.core.designsystem.theme.VideoPlayerComposeTheme
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
        topBar = {
            DesignSystemTopAppBar(
                titleRes = R.string.empty,
                navigationIcon = ImageVector.vectorResource(id = Icons.ArrowBack),
                navigationIconContentDescription = stringResource(id = R.string.back),
                actionIcon = ImageVector.vectorResource(id = Icons.Search),
                actionIconContentDescription = stringResource(id = R.string.search)
            )
        }
    ) { _ ->
        Column(modifier = Modifier.padding(horizontal = Dimens.ScreenPadding)) {
            DesignSystemHeader(titleRes = R.string.recommended_for_you)
            when {
                state.isLoading -> DesignSystemLoading()
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

@Preview("Dark mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("Light mode")
@Composable
fun DefaultPreview() {
    VideoPlayerComposeTheme {
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
}