package com.wcabral.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystemChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit
) {
    SuggestionChip(
        label = { label() },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            labelColor = MaterialTheme.colorScheme.onTertiary
        ),
        onClick = onClick,
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderColor = MaterialTheme.colorScheme.tertiary,
        )
    )
}

@Preview("dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("light mode")
@Composable
fun DesignSystemChip() {
    DesignSystemTheme {
        DesignSystemChip(onClick = {  }) {
            Text(text = "Chip text")
        }
    }
}
