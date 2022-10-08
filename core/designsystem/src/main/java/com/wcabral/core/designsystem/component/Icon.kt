package com.wcabral.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DesignSystemIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    tint: Color = MaterialTheme.colorScheme.onSurface,
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = tint,
    )
}
