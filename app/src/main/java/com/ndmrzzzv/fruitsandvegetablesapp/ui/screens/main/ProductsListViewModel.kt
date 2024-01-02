package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import com.ndmrzzzv.domain.usecase.GetAllProductsUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.activity.BaseViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val internetChecker: InternetChecker
) : BaseViewModel() {

    private val _products = MutableStateFlow<ProductsListState>(ProductsListState.Loading)
    val products = _products.asStateFlow()

    init {
        getAllItems()
    }

    fun getAllItems() {
        scopeWithExceptionHandler.launch {
            _products.value = ProductsListState.Loading
            if (internetChecker.checkConnection()) {
                _products.value = ProductsListState.LoadedData(getAllProductsUseCase())
            } else {
                _products.value = ProductsListState.LoadingFailed("Network error")
            }
        }
    }

}