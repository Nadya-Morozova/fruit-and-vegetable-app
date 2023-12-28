package com.ndmrzzzv.domain.usecase

import com.ndmrzzzv.domain.model.Item
import com.ndmrzzzv.domain.repository.FruitAndVegRepository

class GetAllItemsUseCase(
    private val fruitAndVegRepository: FruitAndVegRepository
) {

    suspend operator fun invoke(): List<Item> {
        return fruitAndVegRepository.getAllItems()
    }

}