package com.example.kharchenko.domain.model

import com.google.gson.annotations.SerializedName

data class GifModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("gifURL")
    val url: String,
    @SerializedName("description")
    val description: String
)
