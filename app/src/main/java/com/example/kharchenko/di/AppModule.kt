package com.example.kharchenko.di

import com.example.kharchenko.data.GifRepositoryImpl
import com.example.kharchenko.domain.usecase.GifDataInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRepository() = GifRepositoryImpl()

    @Provides
    @Singleton
    fun provideInteractor(repository: GifRepositoryImpl) = GifDataInteractorImpl(repository = repository)
}