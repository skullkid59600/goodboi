package com.goodboi.fragments.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodboi.activities.data.Image
import com.goodboi.activities.data.Repository.ImageRepository
import kotlinx.coroutines.launch
import retrofit2.Response


val myResponse: MutableLiveData<Response<Image>> = MutableLiveData()

class ImageViewModel(private val repository: ImageRepository) : ViewModel(){
    fun getImage(){
        viewModelScope.launch {
            var response : Response<Image> = repository.getImage()
            myResponse.value = response
        }
    }
}