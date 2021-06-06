package com.goodboi.activities.data.Repository

import com.goodboi.activities.data.Image
import com.goodboi.activities.data.api.RetrofitInstance
import retrofit2.Response

class ImageRepository {

    suspend fun getImage(): Response<Image> {
        return RetrofitInstance.api.getImage()
    }
}