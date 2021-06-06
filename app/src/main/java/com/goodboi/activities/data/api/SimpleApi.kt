package com.goodboi.activities.data.api

import com.goodboi.activities.data.Image
import retrofit2.http.GET

interface SimpleApi {
    // posts/1
    //api/breeds/image/random
    @GET("api/breeds/image/random")
    suspend fun getImage(): Image
}