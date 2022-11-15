@file:OptIn(ExperimentalMaterial3Api::class)

package com.wcabral.feature.store.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.component.DesignSystemTopAppBar
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.ui.previews.LightAndNightPreviews

@Composable
fun StoreDetailToolbar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: () -> Unit,
) {
    DesignSystemTopAppBar(
        titleRes = R.string.empty_string,
        navigationIcon = ImageVector.vectorResource(id = DesignSystemIcons.ArrowBack),
        navigationIconContentDescription = stringResource(id = R.string.back),
        onNavigationClick = onNavigationClick,
        actionIcon = null,
        actionIconContentDescription = null,
        backgroundColor = Color.Transparent,
        scrollBehavior = scrollBehavior,
    )
}

@LightAndNightPreviews
@Composable
fun GameDetailToolbarPreview() {
    DesignSystemTheme {
        DesignSystemBackground {
            StoreDetailToolbar{}
        }
    }
}
