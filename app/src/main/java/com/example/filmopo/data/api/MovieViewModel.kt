package com.example.filmopo.data.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmopo.data.Constant
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private val restInterface: ApiService
    val state = mutableStateOf(emptyList<MovieData>())

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.omdbapi.com/")
            .build()
        restInterface = retrofit.create(ApiService::class.java)
        getListMovie("s")
    }

    private fun getListMovie(query: String) {
        val call = restInterface.getMovies(query, "51f05115")
        call.enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                if (response.isSuccessful) {
                    val searchData = response.body()
                    searchData?.let {
                        state.value = searchData.data ?: emptyList()
                    }
                } else {
                    Log.e("MovieViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                Log.e("MovieViewModel", "Error: ${t.message}")
            }
        })
    }

    fun searchMovies(query: String, applicationContext: Context) {
        val call = restInterface.getMovies(query, "51f05115")
        call.enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                val searchData = response.body()
                val res = searchData?.res

                if (res == "True") {
                    searchData?.let {
                        state.value = searchData.data ?: emptyList()
                    }
                } else {
                    Toast.makeText(applicationContext, "Movie Not Found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                Log.e("MovieViewModel", "Error: ${t.message}")
            }
        })
    }

    suspend fun getMovieById(movieId: String): MovieDetailData? {
        val deferred = CompletableDeferred<MovieDetailData?>()
        val call = restInterface.getDetailMovies(movieId, "51f05115")
        call.enqueue(object : Callback<MovieDetailData> {
            override fun onResponse(call: Call<MovieDetailData>, response: Response<MovieDetailData>) {
                if (response.isSuccessful) {
                    val movieDetailData = response.body()
                    movieDetailData?.let {
                        deferred.complete(movieDetailData)
                    } ?: run {
                        deferred.complete(null)
                    }
                } else {
                    Log.e("MovieViewModel", "Error: ${response.message()}")
                    deferred.complete(null)
                }
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                Log.e("MovieViewModel", "Error: ${t.message}")
                deferred.complete(null)
            }
        })
        return deferred.await()
    }



    private suspend fun getAdditionalMovieData(movieId: String): MovieDetailData? {
        val deferred = CompletableDeferred<MovieDetailData?>()
        val call = restInterface.getDetailMovies(movieId, "51f05115")
        call.enqueue(object : Callback<MovieDetailData> {
            override fun onResponse(call: Call<MovieDetailData>, response: Response<MovieDetailData>) {
                if (response.isSuccessful) {
                    val movieDetailData = response.body()
                    movieDetailData?.let {
                        deferred.complete(movieDetailData)
                    } ?: run {
                        deferred.complete(null)
                    }
                } else {
                    Log.e("MovieViewModel", "Error: ${response.message()}")
                    deferred.complete(null)
                }
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                Log.e("MovieViewModel", "Error: ${t.message}")
                deferred.complete(null)
            }
        })
        return deferred.await()
    }


}


