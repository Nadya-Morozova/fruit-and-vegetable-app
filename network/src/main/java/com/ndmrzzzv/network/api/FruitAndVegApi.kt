package com.ndmrzzzv.network.api

import com.ndmrzzzv.network.data.DetailItem
import com.ndmrzzzv.network.data.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitAndVegApi {

    @GET("items/random")
    suspend fun getAllItems(): Response

    @GET("texts/{itemId}")
    suspend fun getDetailsOfItem(@Path("itemId") id: String?): DetailItem

}