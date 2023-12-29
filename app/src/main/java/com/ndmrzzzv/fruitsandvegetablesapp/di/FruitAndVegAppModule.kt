package com.ndmrzzzv.fruitsandvegetablesapp.di

import com.ndmrzzzv.domain.usecase.GetAllItemsUseCase
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailsOfItemViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.MainItemsViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { GetAllItemsUseCase(get()) }

    single { GetDetailsOfItemUseCase(get()) }

    single { InternetChecker(androidContext()) }

    viewModel { MainItemsViewModel(get(), get()) }

    viewModel { DetailsOfItemViewModel(get(), get(), get()) }

}