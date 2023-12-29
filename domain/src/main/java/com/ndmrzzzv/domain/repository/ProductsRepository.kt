package com.ndmrzzzv.domain.repository

import com.ndmrzzzv.domain.model.Product

interface ProductsRepository {

    suspend fun getAllProducts(): Pair<String?, List<Product>?>

    suspend fun getDetailsOfProduct(id: String?): String

}