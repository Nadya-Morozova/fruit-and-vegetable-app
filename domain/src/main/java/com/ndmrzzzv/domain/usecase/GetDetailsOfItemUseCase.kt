package com.ndmrzzzv.domain.usecase

import com.ndmrzzzv.domain.repository.ProductsRepository

class GetDetailsOfItemUseCase(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(id: String?): String {
        return productsRepository.getDetailsOfProduct(id)
    }

}