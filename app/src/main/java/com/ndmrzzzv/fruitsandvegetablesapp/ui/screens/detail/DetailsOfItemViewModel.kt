package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker

class DetailsOfItemViewModel(
    private val getDetailsOfItemUseCase: GetDetailsOfItemUseCase,
    private val internetChecker: InternetChecker,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
}