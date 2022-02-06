package com.example.kharchenko.presentation.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kharchenko.databinding.ActivityMainBinding
import com.example.kharchenko.di.ComponentHolder
import com.example.kharchenko.domain.model.GifModel
import com.example.kharchenko.presentation.viewmodel.MainViewModel
import com.example.kharchenko.presentation.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory(ComponentHolder.appComponent.interactor) }
    private val gifViewList = mutableListOf<ImageView>()
    private var currentGifIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigateButtons(binding)
        viewModel.getNewGif()
        observeViewModel(binding)
    }

    private fun setupNavigateButtons(binding: ActivityMainBinding) {
        binding.nextButton.setOnClickListener {
            onNextButtonClicked(binding)
        }
        binding.previousButton.setOnClickListener {
            onPreviousButtonClicked(binding)
        }
    }

    private fun onPreviousButtonClicked(binding: ActivityMainBinding) {
        if (currentGifIndex > 0) {
            currentGifIndex--
            binding.gifViewLayout.removeAllViews()
            showCachedGif(binding)
        }
    }

    private fun onNextButtonClicked(binding: ActivityMainBinding) {
        currentGifIndex++
        binding.gifViewLayout.removeAllViews()
        if (currentGifIndex == gifViewList.lastIndex) {
            viewModel.getNewGif()
        } else {
            currentGifIndex++
            showCachedGif(binding)
        }
    }

    private fun showCachedGif(binding: ActivityMainBinding) {
        binding.gifViewLayout.addView(gifViewList[currentGifIndex])
    }

    private fun observeViewModel(binding: ActivityMainBinding) {
        viewModel.isLoading.observe(this) {
            if (it == true) {
                binding.nextButton.isClickable = false
                showLoadingScreen(binding)
            } else {
                binding.nextButton.isClickable = true
                hideLoadingScreen(binding)
            }
        }
        viewModel.isError.observe(this) {
            if (it == true) {
                showErrorScreen(binding)
            } else {
                hideErrorScreen(binding)
            }
        }
        viewModel.newGif.observe(this) {
            addGifView(binding, it)
        }
    }

    private fun showErrorScreen(binding: ActivityMainBinding) {
    }

    private fun hideErrorScreen(binding: ActivityMainBinding) {
    }

    private fun showLoadingScreen(binding: ActivityMainBinding) {
        binding.loadingScreen.root.visibility = VISIBLE
    }

    private fun hideLoadingScreen(binding: ActivityMainBinding) {
        binding.loadingScreen.root.visibility = GONE
    }

    private fun addGifView(binding: ActivityMainBinding, gifModel: GifModel) {
        val newGifView = ImageView(this)

        Glide.with(this).asGif().load(gifModel.url).listener(object : RequestListener<GifDrawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<GifDrawable>?,
                isFirstResource: Boolean
            ): Boolean {
                viewModel.isLoading.value = false
                viewModel.isError.value = true
                return false
            }

            override fun onResourceReady(
                resource: GifDrawable?,
                model: Any?,
                target: Target<GifDrawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                viewModel.isLoading.value = false
                return false
            }
        })
            .into(newGifView)

        gifViewList.add(newGifView)
        binding.gifViewLayout.addView(newGifView)
    }
}