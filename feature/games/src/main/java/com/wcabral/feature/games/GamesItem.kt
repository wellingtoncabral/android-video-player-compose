package com.wcabral.feature.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRating
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.Platform
import com.wcabral.core.model.PlatformType
import com.wcabral.core.model.previewGame
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun GamesItem(
    game: Game,
    onClick: (Game) -> Unit,
) {
    DesignSystemCard(
        onClick = { onClick(game) },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column {
            GamesItemHeader(game = game)
            GamesItemDetails(game = game)
        }
    }
}

@Composable
fun GamesItemHeader(game: Game) {
    Box {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.backgroundImage)
                .crossfade(true)
                .build(),
            loading = {
                DesignSystemLoading(modifier = Modifier.fillMaxSize())
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
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
fun GamesItemDetails(game: Game) {
    Column(
        modifier = Modifier
            .padding(
                start = DesignSystemDimens.Padding.Medium,
                top = DesignSystemDimens.Padding.Medium,
                end = DesignSystemDimens.Padding.Small,
                bottom = DesignSystemDimens.Padding.Large
            )
    ) {
        GamesItemPlatforms(game.platforms)
        Text(
            text = game.name,
            style = MaterialTheme.typography.headlineSmall,
        )
        DesignSystemRating(game.rating)
    }
}

@Composable
fun GamesItemPlatforms(platforms: List<Platform>) {
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
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(end = DesignSystemDimens.Padding.Small),
                )
            }
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

@LightAndNightPreviews
@Composable
fun GamesItemPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            GamesItem(
                game = previewGame,
                onClick = {},
            )
        }
    }
}
