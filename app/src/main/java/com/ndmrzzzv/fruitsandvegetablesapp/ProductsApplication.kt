package com.ndmrzzzv.fruitsandvegetablesapp

import android.app.Application
import com.ndmrzzzv.fruitsandvegetablesapp.di.appModule
import com.ndmrzzzv.network.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ProductsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProductsApplication)
            modules(listOf(networkModule, appModule))
        }
    }
}