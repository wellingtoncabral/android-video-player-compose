package com.wcabral.feature.videos

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemCard
import com.wcabral.core.designsystem.component.DesignSystemIcon
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemRoundedIconButton
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemColors
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.Game
import com.wcabral.core.model.Platform
import com.wcabral.core.model.PlatformType
import com.wcabral.core.model.previewGame
import com.wcabral.core.model.previewGames

@Composable
fun VideosItem(item: Game) {
    DesignSystemCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            VideoItemHeader(item = item)
            VideoItemDetails(item = item)
        }
    }
}

@Composable
fun VideoItemHeader(item: Game) {
    Box {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.backgroundImage)
                .crossfade(true)
                .build(),
            loading = {
                DesignSystemLoading()
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(DesignSystemDimens.VideoThumbnailHeight)
                .fillMaxWidth()
                .shadow(16.dp)
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
fun VideoItemDetails(item: Game) {
    Column(
        modifier = Modifier
            .padding(
                start = DesignSystemDimens.Padding.Medium,
                top = DesignSystemDimens.Padding.Medium,
                end = DesignSystemDimens.Padding.Small,
                bottom = DesignSystemDimens.Padding.Large
            )
    ) {
        VideosPlatforms(item.platforms)
        Text(
            text = item.name,
            style = MaterialTheme.typography.h5,
        )
        VideosRating(item.rating)
    }
}

@Composable
fun VideosPlatforms(platforms: List<Platform>) {
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
fun VideosRating(value: Float) {
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

internal fun PlatformType.asDrawable() = when (this) {
    PlatformType.UNKNOWN -> null
    PlatformType.PC -> DesignSystemIcons.PlatformWindows
    PlatformType.LINUX -> DesignSystemIcons.PlatformLinux
    PlatformType.APPLE -> DesignSystemIcons.PlatformApple
    PlatformType.XBOX -> DesignSystemIcons.PlatformXbox
    PlatformType.PLAYSTATION -> DesignSystemIcons.PlatformPlaystation
    PlatformType.NINTENDO -> DesignSystemIcons.PlatformNintendo
}

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun VideosItemPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            VideosItem(item = previewGame)
        }
    }
}