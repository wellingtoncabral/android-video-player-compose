package com.wcabral.game.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowRow
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.component.ErrorPage
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.GameDetail
import com.wcabral.core.model.Platform
import com.wcabral.core.model.previewGameDetail
import com.wcabral.core.model.previewGames
import com.wcabral.core.model.previewStores
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
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

@Composable
fun GameDetailScreen(
    state: GameDetailContract.State,
    effectFlow: Flow<GameDetailContract.Effect>?,
    onEventSent: (event: GameDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: GameDetailContract.Effect.Navigation) -> Unit,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

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

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            GamesToolbar(onNavigationClick = { onEventSent(GameDetailContract.Event.BackButtonClicked) })
        },
    ) {
        Column {
            when {
                state.isLoading -> DesignSystemLoading(modifier = Modifier.fillMaxSize())
                state.isError -> ErrorPage(
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
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

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview("light mode")
@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
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
