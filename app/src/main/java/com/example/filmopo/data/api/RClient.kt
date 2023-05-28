package com.example.filmopo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    const val BASE_URL = "http://www.omdbapi.com/"
    val instances:ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}