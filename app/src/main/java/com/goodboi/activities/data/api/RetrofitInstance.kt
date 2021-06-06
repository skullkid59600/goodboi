package com.goodboi.activities.data.api

import com.goodboi.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}