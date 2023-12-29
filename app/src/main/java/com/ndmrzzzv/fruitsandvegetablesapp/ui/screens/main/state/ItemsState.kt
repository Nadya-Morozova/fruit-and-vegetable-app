package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.state

import com.ndmrzzzv.domain.model.Item

sealed class ItemsState {

    class LoadingFailed(val message: String) : ItemsState()

    class LoadedData(val titleAndItems: Pair<String?, List<Item>?>) : ItemsState()

    data object Loading : ItemsState()

}