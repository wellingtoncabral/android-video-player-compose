package com.wcabral.game.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.component.DesignSystemRating
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.GameDetail
import com.wcabral.core.model.previewGameDetail

@Composable
fun GameDetailView(
    gameDetail: GameDetail,
    onNavigateToGameVideos: (gameId: Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {
        GameDetailThumbnail(gameDetail, onNavigateToGameVideos)
        Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Medium))
        Column(modifier = Modifier.padding(DesignSystemDimens.Padding.ScreenAll)) {
            DesignSystemHeader(title = gameDetail.name)
            DesignSystemRating(value = gameDetail.rating)
            Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Small))
            GameDetailPlatforms(platforms = gameDetail.platforms)
            Spacer(modifier = Modifier.size(DesignSystemDimens.Padding.Small))
            Text(
                text = gameDetail.description,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDetailViewPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            GameDetailView(
                gameDetail = previewGameDetail
            ) {}
        }
    }
}
