package com.wcabral.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.wcabral.core.designsystem.theme.DesignSystemColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DesignSystemChip(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Chip(
        colors = ChipDefaults.chipColors(
            backgroundColor = DesignSystemColors.Accent,
            contentColor = DesignSystemColors.White
        ),
        onClick = onClick
    ) {
        content()
    }
}