package com.wcabral.core.designsystem.component

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DesignSystemIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    tint: Color = MaterialTheme.colors.onSurface,
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = tint,
    )
}