package com.example.filmopo.data.api

import com.example.filmopo.data.Constant
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    private const val BASE_URL = Constant.OMDB_BASE_URL
    val instances: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // Set connection timeout
            .readTimeout(2, TimeUnit.MINUTES)    // Set read timeout
            .writeTimeout(2, TimeUnit.MINUTES)   // Set write timeout
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ApiService::class.java)
    }
}