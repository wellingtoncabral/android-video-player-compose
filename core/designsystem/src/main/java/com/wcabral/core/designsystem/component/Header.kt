package com.wcabral.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DesignSystemHeader(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
) {
    DesignSystemHeader(
        title = stringResource(id = titleRes),
        modifier = modifier
    )
}

@Composable
fun DesignSystemHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
    )
}


@Preview
@Composable
fun DesignSystemHeaderPreview() {
    DesignSystemHeader(android.R.string.untitled)
}
