package com.ndmrzzzv.network.module

import com.ndmrzzzv.domain.repository.FruitAndVegRepository
import com.ndmrzzzv.network.api.FruitAndVegApi
import com.ndmrzzzv.network.repository.FruitAndVegRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://test-task-server.mediolanum.f17y.com/"

val networkModule = module {

    factory<FruitAndVegRepository> { FruitAndVegRepositoryImpl(get()) }

    single<FruitAndVegApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@single retrofit.create(FruitAndVegApi::class.java)
    }

}