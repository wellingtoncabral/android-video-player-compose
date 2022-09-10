package com.wcabral.feature.videos

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.component.DesignSystemButton
import com.wcabral.core.designsystem.component.DesignSystemHeader
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme

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

@Preview("night mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun VideosErrorPreview() {
    DesignSystemTheme {
        VideosError{}
    }
}
