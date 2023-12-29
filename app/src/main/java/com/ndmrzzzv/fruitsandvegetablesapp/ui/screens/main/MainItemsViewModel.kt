package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import com.ndmrzzzv.domain.usecase.GetAllItemsUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.activity.BaseViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.state.ItemsState
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainItemsViewModel(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val internetChecker: InternetChecker
) : BaseViewModel() {

    private val _products = MutableStateFlow<ItemsState>(ItemsState.Loading)
    val products = _products.asStateFlow()

    fun getAllItems() {
        scopeWithExceptionHandler.launch {
            _products.value = ItemsState.Loading
            if (internetChecker.checkConnection()) {
                _products.value = ItemsState.LoadedData(getAllItemsUseCase())
            } else {
                _products.value = ItemsState.LoadingFailed("Network error")
            }
        }
    }

}