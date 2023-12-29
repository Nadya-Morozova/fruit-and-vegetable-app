package com.ndmrzzzv.network.module

import com.ndmrzzzv.domain.repository.ProductsRepository
import com.ndmrzzzv.network.api.ProductsApi
import com.ndmrzzzv.network.repository.ProductsRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://test-task-server.mediolanum.f17y.com/"

val networkModule = module {

    factory<ProductsRepository> { ProductsRepositoryImpl(get()) }

    single<ProductsApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@single retrofit.create(ProductsApi::class.java)
    }

}