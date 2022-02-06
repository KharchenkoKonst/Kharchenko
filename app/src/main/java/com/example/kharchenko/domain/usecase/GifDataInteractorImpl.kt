package com.example.kharchenko.domain.usecase

import com.example.kharchenko.domain.repository.GifRepository

class GifDataInteractorImpl(
    private val repository: GifRepository
) : GifDataInteractor {

    override suspend fun getGifData() = repository.getGifData()
}