package com.example.filmopo.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    fun getMovies(
        @Query("s") s: String?,
        @Query("apikey") apikey: String
    ): Call<SearchData>

    @GET("/")
    fun getDetailMovies(
        @Query("i") i: String?,
        @Query("apikey") apikey: String
    ): Call<MovieDetailData>
}


