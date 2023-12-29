package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.state

import com.ndmrzzzv.domain.model.DetailItem

sealed class DetailItemState {

    class LoadingFailed(val message: String) : DetailItemState()

    class LoadedData(val item: DetailItem?) : DetailItemState()

    object Loading : DetailItemState()

}