package com.wcabral.feature.videos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.model.Video

@Composable
fun VideosList(
    currentMediaId: String?,
    videos: List<Video>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(DesignSystemDimens.Padding.Medium),
        modifier = Modifier.padding(horizontal = DesignSystemDimens.Padding.ScreenHorizontal)
    ) {

        item {
            DesignSystemHeader(
                modifier = Modifier.padding(top = DesignSystemDimens.Padding.Medium),
                title = stringResource(id = R.string.trailers, formatArgs = arrayOf(videos.size)),
            )
        }

        items(videos, key = { it.id }) { video ->
            VideosItem(
                video,
                isPlaying = currentMediaId == video.id.toString()
            )
        }
    }
}
