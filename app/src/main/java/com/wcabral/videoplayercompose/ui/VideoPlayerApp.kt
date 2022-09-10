package com.wcabral.videoplayercompose.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.wcabral.core.designsystem.component.DesignSystemBackground
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoPlayerApp() {
    DesignSystemTheme {
        DesignSystemBackground {
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                contentColor = MaterialTheme.colors.onBackground
            ) { padding ->

            }
        }
    }
}