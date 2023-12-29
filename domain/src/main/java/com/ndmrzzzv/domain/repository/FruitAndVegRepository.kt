package com.ndmrzzzv.domain.repository

import com.ndmrzzzv.domain.model.Item

interface FruitAndVegRepository {

    suspend fun getAllItems(): Pair<String?, List<Item>?>

    suspend fun getDetailsOfItem(id: String?): String
}