package com.example.kharchenko.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kharchenko.domain.usecase.GifDataInteractorImpl

class MainViewModelFactory(private val interactor: GifDataInteractorImpl) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(interactor) as T
}