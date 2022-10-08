package com.wcabral.feature.store.detail

import androidx.lifecycle.viewModelScope
import com.wcabral.core.common.Result
import com.wcabral.core.common.asResult
import com.wcabral.core.data.repository.StoresRepository
import com.wcabral.core.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StoreDetailViewModel(
    private val storeId: Int,
    private val storeRepository: StoresRepository,
) : BaseViewModel<StoreDetailContract.Event, StoreDetailContract.State, StoreDetailContract.Effect>() {

    init {
        fetchGameDetail()
    }

    override fun setInitialState() = StoreDetailContract.State(
        store = null,
        isLoading = false,
        isError = false,
    )

    override fun handleEvents(event: StoreDetailContract.Event) {
        when (event) {
            is StoreDetailContract.Event.Retry -> fetchGameDetail()
            is StoreDetailContract.Event.BackButtonClicked -> setEffect {
                StoreDetailContract.Effect.Navigation.Back
            }
        }
    }

    private fun fetchGameDetail() {
        viewModelScope.launch {
            storeRepository.getStoreDetail(storeId)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState { copy(isLoading = true, isError = false) }
                        }
                        is Result.Success -> {
                            setState { copy(isLoading = false, store = result.data) }
                        }
                        is Result.Error -> {
                            setState { copy(isLoading = false, isError = true) }
                        }
                    }
                }.collect()
        }
    }
}
