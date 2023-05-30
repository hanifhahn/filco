package com.example.filmopo.data.api

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("Search") val data:List<MovieData>,
    @SerializedName("totalResults") val totalData:Int,
    @SerializedName("Response") val res:String,
)
