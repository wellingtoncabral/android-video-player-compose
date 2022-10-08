package com.wcabral.feature.store.detail

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.wcabral.core.designsystem.component.DesignSystemLoading
import com.wcabral.core.designsystem.component.DesignSystemErrorPage
import com.wcabral.core.designsystem.theme.DesignSystemTheme
import com.wcabral.core.model.previewStore
import com.wcabral.core.ui.SIDE_EFFECTS_KEY
import com.wcabral.core.ui.previews.DevicePreviews
import com.wcabral.core.ui.previews.LightAndNightPreviews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StoreDetailRoute(
    storeId: Int,
    onBackClick: () -> Unit,
) {
    val viewModel = getViewModel<StoreDetailViewModel> { parametersOf(storeId) }
    StoreDetailScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is StoreDetailContract.Effect.Navigation.Back -> onBackClick()
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDetailScreen(
    state: StoreDetailContract.State,
    effectFlow: Flow<StoreDetailContract.Effect>?,
    onEventSent: (event: StoreDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: StoreDetailContract.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is StoreDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(StoreDetailContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarScrollState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            StoreDetailToolbar(
                scrollBehavior = scrollBehavior,
                onNavigationClick = { onEventSent(StoreDetailContract.Event.BackButtonClicked) }
            )
        },
    ) {
        Column {
            when {
                state.isLoading -> DesignSystemLoading(modifier = Modifier.fillMaxSize())
                state.isError -> DesignSystemErrorPage(
                    titleRes = R.string.generic_error_title,
                    descriptionRes = R.string.generic_error_description,
                    buttonTitleRes = R.string.generic_error_button_title,
                ) {
                    onEventSent(StoreDetailContract.Event.Retry)
                }
                state.store != null -> {
                    StoreDetailView(store = state.store)
                }
            }
        }
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun StoreDetailScreenPopulated() {
    DesignSystemTheme {
        StoreDetailScreen(
            state = StoreDetailContract.State(
                store = previewStore,
                isLoading = false,
                isError = false
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun StoreDetailScreenLoading() {
    DesignSystemTheme {
        StoreDetailScreen(
            state = StoreDetailContract.State(
                store = null,
                isLoading = true,
                isError = false
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}

@DevicePreviews
@LightAndNightPreviews
@Composable
fun StoreDetailScreenError() {
    DesignSystemTheme {
        StoreDetailScreen(
            state = StoreDetailContract.State(
                store = null,
                isLoading = false,
                isError = true
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {},
        )
    }
}
