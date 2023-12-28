package com.ndmrzzzv.network.repository

import com.ndmrzzzv.domain.model.Item
import com.ndmrzzzv.domain.repository.FruitAndVegRepository
import com.ndmrzzzv.network.api.FruitAndVegApi

class FruitAndVegRepositoryImpl(
    private val fruitAndVegApi: FruitAndVegApi
) : FruitAndVegRepository {

    override suspend fun getAllItems(): List<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsOfItem(id: String?): Item {
        TODO("Not yet implemented")
    }

}