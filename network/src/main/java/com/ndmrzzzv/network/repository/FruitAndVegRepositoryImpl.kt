package com.ndmrzzzv.network.repository

import com.ndmrzzzv.domain.model.DetailItem
import com.ndmrzzzv.domain.model.Item
import com.ndmrzzzv.domain.repository.FruitAndVegRepository
import com.ndmrzzzv.network.api.FruitAndVegApi

class FruitAndVegRepositoryImpl(
    private val fruitAndVegApi: FruitAndVegApi
) : FruitAndVegRepository {

    override suspend fun getAllItems(): Pair<String?, List<Item>?> {
        val result = fruitAndVegApi.getAllItems()
        val newListOfItems = result.items?.map {
            val color = android.graphics.Color.parseColor("#${it.color}")
            Item(it.id, it.name, it.image, color)
        }
        return result.title to newListOfItems
    }

    override suspend fun getDetailsOfItem(id: String?): DetailItem {
        val result = fruitAndVegApi.getDetailsOfItem(id)
        return DetailItem(result.id, result.text)
    }

}