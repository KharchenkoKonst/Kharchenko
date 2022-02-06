package com.example.kharchenko.domain.repository

import com.example.kharchenko.domain.model.GifModel

interface GifRepository {

    suspend fun getGifData(): GifModel
}