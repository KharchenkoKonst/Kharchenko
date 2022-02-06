package com.example.kharchenko.data

import com.example.kharchenko.data.network.ApiService
import com.example.kharchenko.data.network.ApiService.Companion.BASE_URL
import com.example.kharchenko.domain.repository.GifRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GifRepositoryImpl : GifRepository {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    override suspend fun getGifData() = api.getData()
}