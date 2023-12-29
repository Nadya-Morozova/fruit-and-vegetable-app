package com.ndmrzzzv.domain.model

data class DetailProduct(
    val id: String?,
    val name: String?,
    val image: String?,
    val color: Int?,
    val text: String?
) {

    companion object {
        fun create(product: Product, description: String?): DetailProduct {
            return DetailProduct(
                id = product.id,
                name = product.name,
                image = product.image,
                color = product.color,
                text = description
            )
        }
    }

}