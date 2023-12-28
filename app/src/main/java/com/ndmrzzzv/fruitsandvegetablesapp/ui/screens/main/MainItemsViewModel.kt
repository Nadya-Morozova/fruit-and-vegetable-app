package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.domain.usecase.GetAllItemsUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker

class MainItemsViewModel(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val internetChecker: InternetChecker
) : ViewModel() {

}