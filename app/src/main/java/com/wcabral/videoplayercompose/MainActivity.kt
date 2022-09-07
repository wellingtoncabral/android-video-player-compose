package com.wcabral.videoplayercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.wcabral.core.designsystem.theme.VideoPlayerComposeTheme
import com.wcabral.feature.videos.videos.VideosScreen
import com.wcabral.feature.videos.videos.VideosViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = getViewModel<VideosViewModel>()
        setContent {
            VideoPlayerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    VideosScreen(
                        state = viewModel.viewState.value,
                        effectFlow = viewModel.effect,
                        onEventSent = { event ->  viewModel.setEvent(event) },
                        onNavigationRequested = {},
                    )
                }
            }
        }
    }
}
