package com.example.filmopo.data.api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmopo.data.Constant
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel(), ViewModelProvider.Factory {
    private val api: ApiService = ApiService.getInstance()
    var listMovie : List<Movie> by mutableStateOf(listOf())
    var movie : Movie? =null

    init {
        getListMovie("spiderman")
    }

    private fun getListMovie(searchMovie: String){
        viewModelScope.launch {
            try {
                listMovie = api.getMovie(searchMovie, "movie", Constant.OMDB_API_KEY).Search
            } catch (e: Exception){
                Log.d("error:", e.message.toString())
            }
        }
    }

    fun getDetailMovie(imdbID: Int){
        viewModelScope.launch {
            try {
                movie = listMovie.first{it.imdbID == imdbID}
            } catch (e: Exception){
                Log.d("error:", e.message.toString())
            }
        }
    }
}