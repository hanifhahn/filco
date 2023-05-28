package com.example.filmopo.data.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("?s=spider&apikey=51f05115")

    fun getMovies() : Call<SearchData>
}

