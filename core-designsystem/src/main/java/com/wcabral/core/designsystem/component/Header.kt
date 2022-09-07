package com.wcabral.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DesignSystemHeader(
    @StringRes titleRes: Int,
) {
    Text(
        text = stringResource(id = titleRes),
        style = MaterialTheme.typography.h6
    )
}

@Preview
@Composable
fun DesignSystemHeaderPreview() {
    DesignSystemHeader(android.R.string.untitled)
}
