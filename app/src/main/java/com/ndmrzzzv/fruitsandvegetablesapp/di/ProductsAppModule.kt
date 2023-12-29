package com.ndmrzzzv.fruitsandvegetablesapp.di

import com.ndmrzzzv.domain.usecase.GetAllProductsUseCase
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailProductViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.ProductsListViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { GetAllProductsUseCase(get()) }

    single { GetDetailsOfItemUseCase(get()) }

    single { InternetChecker(androidContext()) }

    viewModel { ProductsListViewModel(get(), get()) }

    viewModel { DetailProductViewModel(get(), get(), get()) }

}