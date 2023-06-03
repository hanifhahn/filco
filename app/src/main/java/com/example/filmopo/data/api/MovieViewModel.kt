package com.example.filmopo.data.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private val restInterface: ApiService
    val state = mutableStateOf(emptyList<MovieData>())

    private val database = FirebaseDatabase.getInstance().getReference("MovieByUser")
    private val readDataFromFirebase = MutableLiveData<List<MovieData>>()
    val data: LiveData<List<MovieData>> get() = readDataFromFirebase

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.omdbapi.com/")
            .build()
        restInterface = retrofit.create(ApiService::class.java)
        getListMovie("s")
    }

    fun fetchData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<MovieData>()
                for (childSnapshot in snapshot.children) {
                    val dataItem = childSnapshot.getValue(MovieData::class.java)
                    dataItem?.let {
                        dataList.add(it)
                    }
                }
                readDataFromFirebase.value = dataList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("fetchData : ", error.toString())
            }
        })
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


