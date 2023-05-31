package com.example.filmopo.data.movie_by_user

import android.util.Log
import com.example.filmopo.data.api.MovieDetailData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



object MovieByUser {
    private val context = "MovieByUser"

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val moviesRef: DatabaseReference = database.getReference("MovieByUser")

    private val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser!!.uid

    fun createMovie(movieDetailData: MovieDetailData?) {
        val movieSaveId = uid + "-" + movieDetailData!!.imdbID // Generate a unique key for the film
        movieSaveId?.let {
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