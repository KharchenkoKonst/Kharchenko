package com.example.kharchenko.data.network

import com.example.kharchenko.domain.model.GifModel
import retrofit2.http.GET

interface ApiService {

    @GET("/random?json=true")
    suspend fun getData(): GifModel

    companion object{
        const val BASE_URL = "https://developerslife.ru"
    }
}