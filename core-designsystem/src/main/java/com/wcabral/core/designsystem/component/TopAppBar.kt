package com.wcabral.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.VideoPlayerComposeTheme

@Composable
fun VideoPlayerAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 0.dp,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                )
            }
        },
        backgroundColor = backgroundColor,
        modifier = modifier,
        elevation = elevation,
    )
}

@Preview("Top App Bar - Dark mode", uiMode = UI_MODE_NIGHT_YES)
@Preview("Top App Bar - Light mode")
@Composable
fun NiaTopAppBarPreview() {
    VideoPlayerComposeTheme {
        VideoPlayerAppBar(
            titleRes = android.R.string.untitled,
            navigationIcon = Icons.Default.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = Icons.Default.MoreVert,
            actionIconContentDescription = "Action icon"
        )
    }
}
