package com.goodboi.activities.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodboi.activities.data.Repository.ImageRepository
import com.goodboi.fragments.viewModel.ImageViewModel

class ImageViewModelFactory(
    private val repository: ImageRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel(repository) as T
    }

}