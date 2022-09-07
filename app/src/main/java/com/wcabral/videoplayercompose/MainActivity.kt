package com.wcabral.videoplayercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VideoPlayerComposeTheme {
        Greeting("Android")
    }
}
