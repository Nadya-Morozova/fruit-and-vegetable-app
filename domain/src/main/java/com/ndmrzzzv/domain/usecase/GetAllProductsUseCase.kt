package com.ndmrzzzv.domain.usecase

import com.ndmrzzzv.domain.model.Product
import com.ndmrzzzv.domain.repository.ProductsRepository

class GetAllProductsUseCase(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(): Pair<String?, List<Product>?> {
        return productsRepository.getAllProducts()
    }

}