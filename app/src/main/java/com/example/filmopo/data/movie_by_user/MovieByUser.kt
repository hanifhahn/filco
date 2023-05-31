package com.example.filmopo.data.movie_by_user

import android.util.Log
import com.example.filmopo.data.api.MovieDetailData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object MovieByUser {
    private val context = "MovieByUser"

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val moviesRef: DatabaseReference = database.getReference("MovieByUser")

//    fun createMovie(movieData: MovieData) {
//        val movieRef = moviesRef.push()
//        movieData.imdbID = movieRef.key.toString()
//        movieRef.setValue(movieData)
//    }

    fun createMovie(movieDetailData: MovieDetailData?) {
        val movieId = moviesRef.push().key // Generate a unique key for the film
        movieId?.let {
            moviesRef.child(it).setValue(movieDetailData)
                .addOnSuccessListener {
                    Log.d(context, "Movie Saved")
                }
                .addOnFailureListener { e ->
                    Log.d(context, e.toString())
                }
        }
    }

}