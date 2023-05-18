package com.example.filmopo.data.api

import com.example.filmopo.data.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/Search")
    suspend fun getMovie(
        @Query("s") query: String,
        @Query("type") type: String,
        @Query("apikey") apiKey: String
    ) : DataMovie

    companion object {
        private var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(Constant.OMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}