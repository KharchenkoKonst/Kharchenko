package com.example.kharchenko.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kharchenko.domain.model.GifModel
import com.example.kharchenko.domain.usecase.GifDataInteractor
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: GifDataInteractor) : ViewModel() {

    val newGif = MutableLiveData<GifModel>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    init {
        isError.value = false
    }

    fun getNewGif() {
        viewModelScope.launch {
            try {

                isLoading.postValue(true)
                newGif.postValue(interactor.getGifData())
            } catch (e: Exception) {
                isLoading.postValue(false)
                isError.postValue(true)
            }
        }
    }
}