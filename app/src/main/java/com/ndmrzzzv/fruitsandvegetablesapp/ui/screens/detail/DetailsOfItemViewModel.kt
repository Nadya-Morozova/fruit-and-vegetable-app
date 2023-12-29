package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import com.ndmrzzzv.domain.model.DetailItem
import com.ndmrzzzv.domain.model.Item
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.activity.BaseViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.state.DetailItemState
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsOfItemViewModel(
    private val getDetailsOfItemUseCase: GetDetailsOfItemUseCase,
    private val internetChecker: InternetChecker,
    private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _product = MutableStateFlow<DetailItemState>(DetailItemState.Loading)
    val product = _product.asStateFlow()


    init {
        getDetailOfProduct()
    }

    fun getDetailOfProduct() {
        val item = stateHandle.get<Item>("item")
        scopeWithExceptionHandler.launch {
            if (internetChecker.checkConnection()) {
                val description = getDetailsOfItemUseCase(item?.id)
                val detailProduct =
                    DetailItem(item?.id, item?.name, item?.image, item?.color, description)
                _product.value = DetailItemState.LoadedData(detailProduct)
            } else {
                _product.value = DetailItemState.LoadingFailed("Network error")
            }
        }
    }

}