package com.ndmrzzzv.domain.model

data class DetailItem(
    val id: String?,
    val name: String?,
    val image: String?,
    val color: Int?,
    val text: String?
) {

    companion object {
        fun create(item: Item, description: String?): DetailItem {
            return DetailItem(
                id = item.id,
                name = item.name,
                image = item.image,
                color = item.color,
                text = description
            )
        }
    }

}