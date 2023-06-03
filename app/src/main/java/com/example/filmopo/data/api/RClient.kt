package com.example.filmopo.data.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    const val BASE_URL = "http://www.omdbapi.com/"
    val instances: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // Set connection timeout
            .readTimeout(1, TimeUnit.MINUTES)    // Set read timeout
            .writeTimeout(1, TimeUnit.MINUTES)   // Set write timeout
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ApiService::class.java)
    }
}