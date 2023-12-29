package com.ndmrzzzv.network.api

import com.ndmrzzzv.network.data.DetailProduct
import com.ndmrzzzv.network.data.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("items/random")
    suspend fun getAllProducts(): Response

    @GET("texts/{itemId}")
    suspend fun getDetailsOfProduct(@Path("itemId") id: String?): DetailProduct

}