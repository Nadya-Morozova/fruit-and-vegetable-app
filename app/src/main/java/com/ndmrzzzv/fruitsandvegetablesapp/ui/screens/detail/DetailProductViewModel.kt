package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.ndmrzzzv.domain.model.DetailProduct
import com.ndmrzzzv.domain.model.Product
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.activity.BaseViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URLDecoder

class DetailProductViewModel(
    private val getDetailsOfItemUseCase: GetDetailsOfItemUseCase,
    private val internetChecker: InternetChecker,
    private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _product = MutableStateFlow<DetailProductState>(DetailProductState.Loading)
    val product = _product.asStateFlow()

    private val gson = Gson()

    init {
        getDetailOfProduct()
    }

    fun getDetailOfProduct() {
        _product.value = DetailProductState.Loading
        val itemString = stateHandle.get<String>("item")
        if (!itemString.isNullOrBlank()) {
            val itemDecode = URLDecoder.decode(itemString, "utf-8")
            val product = gson.fromJson(itemDecode, Product::class.java)
            scopeWithExceptionHandler.launch {
                if (internetChecker.checkConnection()) {
                    val description = getDetailsOfItemUseCase(product?.id)
                    val detailProduct = DetailProduct.create(product, description)
                    _product.value = DetailProductState.LoadedData(detailProduct)
                } else {
                    _product.value = DetailProductState.LoadingFailed("Network error")
                }
            }
        }

    }

}