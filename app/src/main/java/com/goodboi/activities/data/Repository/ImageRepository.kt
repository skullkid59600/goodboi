package com.goodboi.activities.data.Repository

import com.goodboi.activities.data.Image
import com.goodboi.activities.data.api.RetrofitInstance

class ImageRepository {

    suspend fun getImage(): Image {
        return RetrofitInstance.api.getImage()
    }
}