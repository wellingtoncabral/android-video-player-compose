package com.wcabral.feature.videos.videos

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wcabral.core.designsystem.component.DesignSystemButton
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.component.DesignSystemIcon
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.component.DesignSystemTopAppBar
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemColors
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.Platform
import com.wcabral.core.model.PlatformType
import com.wcabral.core.model.previewGames
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.feature.videos.R
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
        }
    ) {
        Column(modifier = Modifier.padding(horizontal = DesignSystemDimens.ScreenPadding)) {
            when {
                state.isLoading -> DesignSystemLoading()
                state.isError -> VideosError(
                    onRetryClick = { onEventSent(VideosContract.Event.Retry) }
                )
                else -> {
                    DesignSystemHeader(
                        titleRes = R.string.recommended_for_you,
                        modifier = Modifier.padding(vertical = DesignSystemDimens.ScreenPadding)
                    )
                    VideosList(list = state.videos)
                }
            }
        }
    }
}

@Composable
fun VideosToolbar(onNavigationClick: () -> Unit) {
    DesignSystemTopAppBar(
        titleRes = R.string.empty,
        navigationIcon = ImageVector.vectorResource(id = DesignSystemIcons.ArrowBack),
        navigationIconContentDescription = stringResource(id = R.string.back),
        onNavigationClick = onNavigationClick,
        actionIcon = ImageVector.vectorResource(id = DesignSystemIcons.Search),
        actionIconContentDescription = stringResource(id = R.string.search)
    )
}

@Composable
fun VideosList(list: List<Game>) {
    val columns = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 2
        else -> 1
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        horizontalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Small),
    ) {
        items(list, key = { it.id }) { item ->
            VideoItemCard(item = item)
        }
    }
}

@Composable
fun VideoItemCard(item: Game) {
    DesignSystemCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            VideoItemCardHeader(item = item)
            VideoItemCardDetails(item = item)
        }
    }
}

@Composable
fun VideoItemCardHeader(item: Game) {
    Box {
        AsyncImage(
            model = item.backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(DesignSystemDimens.VideoThumbnailHeight)
                .fillMaxWidth()
                .shadow(12.dp)
        )
        DesignSystemRoundedIconButton(
            icon = DesignSystemIcons.PlayArrow,
            contentDescription = null,
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(DesignSystemDimens.Padding.Medium)
        )
    }
}

@Composable
fun VideoItemCardDetails(item: Game) {
    Column(
        modifier = Modifier.padding(
            start = DesignSystemDimens.Padding.Medium,
            top = DesignSystemDimens.Padding.Medium,
            end = DesignSystemDimens.Padding.Small,
            bottom = DesignSystemDimens.Padding.Large
        )
    ) {
        VideoPlatforms(item.platforms)
        Text(
            text = item.name,
            style = MaterialTheme.typography.h5,
        )
        VideoRating(item.rating)
    }
}

@Composable
fun VideoPlatforms(platforms: List<Platform>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DesignSystemDimens.Padding.Small)
    ) {
        platforms.onEach { item ->
            item.platformType.asDrawable()?.let { icon ->
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = item.name,
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(end = DesignSystemDimens.Padding.Small),
                )
            }
        }
    }
}

@Composable
fun VideoRating(value: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = DesignSystemDimens.Padding.ExtraSmall)
    ) {
        DesignSystemIcon(
            imageVector = DesignSystemIcons.Star,
            contentDescription = null,
            tint = DesignSystemColors.Star
        )
        Spacer(modifier = Modifier.padding(horizontal = DesignSystemDimens.Padding.ExtraSmall))
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@Composable
fun VideosError(
    onRetryClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        DesignSystemHeader(
            titleRes = R.string.generic_error_title,
        )
        Text(
            text = stringResource(id = R.string.generic_error_description),
            modifier = Modifier.padding(DesignSystemDimens.Padding.ExtraSmall)
        )
        DesignSystemButton(
            modifier = Modifier.padding(DesignSystemDimens.Padding.Large),
            onClick = onRetryClick,
        ) {
            Text(text = stringResource(id = R.string.generic_error_button_title))
        }
    }
}

internal fun PlatformType.asDrawable() = when (this) {
    PlatformType.UNKNOWN -> null
    PlatformType.PC -> DesignSystemIcons.PlatformWindows
    PlatformType.LINUX -> DesignSystemIcons.PlatformLinux
    PlatformType.APPLE -> DesignSystemIcons.PlatformApple
    PlatformType.XBOX -> DesignSystemIcons.PlatformXbox
    PlatformType.PLAYSTATION -> DesignSystemIcons.PlatformPlaystation
    PlatformType.NINTENDO -> DesignSystemIcons.PlatformNintendo
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