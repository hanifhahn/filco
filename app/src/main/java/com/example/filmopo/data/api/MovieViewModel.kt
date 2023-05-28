package com.example.filmopo.data.api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmopo.data.Constant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel(private val stateHandle: SavedStateHandle) : ViewModel(){
    private var restInterface: ApiService
    val state = mutableStateOf(emptyList<MovieData>())
    private lateinit var movieCall: Call<SearchData>
    val list = ArrayList<MovieData>()

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(
                RClient.BASE_URL
            )
            .build()
        restInterface = retrofit.create(
            ApiService::class.java
        )
        getListMovie()
    }

    private fun getListMovie(){
        movieCall = restInterface.getMovies()
        movieCall.enqueue(
            object : Callback<SearchData> {
                override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                    response.body()?.let { list.addAll(it.data)
                        state.value = it.data
                    }
                }
                override fun onFailure(call: Call<SearchData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

//    fun getDetailMovie(imdbID: Int){
//        viewModelScope.launch {
//            try {
//                movie = listMovie.first{it.imdbID == imdbID}
//            } catch (e: Exception){
//                Log.d("error:", e.message.toString())
//            }
//        }
//    }
}
