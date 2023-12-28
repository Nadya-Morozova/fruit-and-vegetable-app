package com.ndmrzzzv.network.data

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("title") val title: String?,
    @SerializedName("items") val items: List<ListItem>?
)

data class ListItem(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("color") val color: String?
)
