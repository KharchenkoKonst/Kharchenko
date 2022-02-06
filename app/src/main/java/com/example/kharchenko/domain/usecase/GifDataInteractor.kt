package com.example.kharchenko.domain.usecase

import com.example.kharchenko.domain.model.GifModel

interface GifDataInteractor {
    suspend fun getGifData(): GifModel
}