package com.wcabral.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemErrorPage(
    titleRes: Int,
    descriptionRes: Int,
    buttonTitleRes: Int,
    onRetryClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        DesignSystemHeader(
            titleRes = titleRes
        )
        Text(
            text = stringResource(id = descriptionRes),
            modifier = Modifier.padding(DesignSystemDimens.Padding.ExtraSmall)
        )
        DesignSystemPrimaryButton(
            modifier = Modifier.padding(DesignSystemDimens.Padding.Large),
            onClick = onRetryClick,
        ) {
            Text(text = stringResource(id = buttonTitleRes))
        }
    }
}

@Preview("night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun ErrorPagePreview() {
    DesignSystemTheme {
        DesignSystemErrorPage(
            titleRes = android.R.string.untitled,
            descriptionRes = android.R.string.untitled,
            buttonTitleRes = android.R.string.untitled
        ){}
    }
}