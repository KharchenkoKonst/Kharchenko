package com.example.kharchenko.presentation.viewmodel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.kharchenko.databinding.GifViewBinding

class GifView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val layoutInflater = LayoutInflater.from(context)
    private val binding = GifViewBinding.inflate(LayoutInflater.from(context), this, true)

    private val gifViewList = mutableListOf<ImageView>()
    private var currentImageViewIndex = -1

    fun setPreviousGif() {
        binding.root.removeAllViews()

    }

    fun setNextGif() {
        binding.root.removeAllViews()

    }

    private companion object {

        const val NOT_INITIALIZED = -1
    }
}