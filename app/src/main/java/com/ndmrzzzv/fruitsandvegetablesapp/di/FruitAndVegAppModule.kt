package com.ndmrzzzv.fruitsandvegetablesapp.di

import com.ndmrzzzv.domain.usecase.GetAllItemsUseCase
import com.ndmrzzzv.domain.usecase.GetDetailsOfItemUseCase
import com.ndmrzzzv.fruitsandvegetablesapp.utils.InternetChecker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { GetAllItemsUseCase(get()) }

    single { GetDetailsOfItemUseCase(get()) }

    single { InternetChecker(androidContext()) }

}