package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import com.ndmrzzzv.domain.model.DetailProduct

sealed class DetailProductState {

    class LoadingFailed(val message: String) : DetailProductState()

    class LoadedData(val product: DetailProduct?) : DetailProductState()

    object Loading : DetailProductState()

}