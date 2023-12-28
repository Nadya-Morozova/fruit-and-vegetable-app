package com.ndmrzzzv.network.data

import com.google.gson.annotations.SerializedName

class DetailItem(
    @SerializedName("id") val id: String?,
    @SerializedName("text") val text: String
)