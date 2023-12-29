package com.ndmrzzzv.domain.usecase

import com.ndmrzzzv.domain.repository.FruitAndVegRepository

class GetDetailsOfItemUseCase(
    private val fruitAndVegRepository: FruitAndVegRepository
) {

    suspend operator fun invoke(id: String?): String {
        return fruitAndVegRepository.getDetailsOfItem(id)
    }

}