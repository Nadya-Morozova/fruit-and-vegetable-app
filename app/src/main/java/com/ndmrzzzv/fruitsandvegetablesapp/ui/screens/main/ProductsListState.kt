package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import com.ndmrzzzv.domain.model.Product

sealed class ProductsListState {

    class LoadingFailed(val message: String) : ProductsListState()

    class LoadedData(val data: Pair<String?, List<Product>?>) : ProductsListState()

    data object Loading : ProductsListState()

}