package com.example.kharchenko.di

import com.example.kharchenko.data.GifRepositoryImpl
import com.example.kharchenko.domain.usecase.GifDataInteractorImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    val repository: GifRepositoryImpl
    val interactor: GifDataInteractorImpl
}