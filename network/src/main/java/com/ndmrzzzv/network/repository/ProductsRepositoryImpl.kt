package com.ndmrzzzv.network.repository

import com.ndmrzzzv.domain.model.Product
import com.ndmrzzzv.domain.repository.ProductsRepository
import com.ndmrzzzv.network.api.ProductsApi

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductsRepository {

    override suspend fun getAllProducts(): Pair<String?, List<Product>?> {
        val result = productsApi.getAllProducts()
        val newListOfProducts = result.items?.map {
            val color = try {
                android.graphics.Color.parseColor("#${it.color}")
            } catch (exception: Exception) {
                0
            }
            Product(
                it.id,
                it.name,
                "https://test-task-server.mediolanum.f17y.com${it.image}",
                color
            )
        }
        return result.title to newListOfProducts
    }

    override suspend fun getDetailsOfProduct(id: String?): String {
        val result = productsApi.getDetailsOfProduct(id)
        return result.text
    }

}