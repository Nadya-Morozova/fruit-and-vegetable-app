package com.ndmrzzzv.domain.usecase

import com.ndmrzzzv.domain.model.DetailItem
import com.ndmrzzzv.domain.repository.FruitAndVegRepository

class GetDetailsOfItemUseCase(
    private val fruitAndVegRepository: FruitAndVegRepository
) {

    suspend operator fun invoke(id: String?): DetailItem {
        return fruitAndVegRepository.getDetailsOfItem(id)
    }

}