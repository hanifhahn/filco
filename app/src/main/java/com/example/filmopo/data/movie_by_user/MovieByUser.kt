package com.example.filmopo.data.movie_by_user

import android.util.Log
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.LocalContext
import com.example.filmopo.data.api.MovieDetailData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object MovieByUser {
    private const val thisClass = "MovieByUser"

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val moviesRef: DatabaseReference = database.getReference("MovieByUser")

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val uid = currentUser!!.uid

    fun createMovie(movieDetailData: MovieDetailData?) {
        val movieSaveId = uid + "-" + movieDetailData!!.imdbID
        movieSaveId.let {
            moviesRef.child(it).setValue(movieDetailData)
                .addOnSuccessListener {
                    Log.d(thisClass, "Movie Saved")
                }
                .addOnFailureListener { e ->
                    Log.d(thisClass, e.toString())
                }
        }
    }

    fun retrieveMovies(callback: (List<MovieDetailData>) -> Unit) {
        moviesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movies = mutableListOf<MovieDetailData>()
                for (movieSnapshot in dataSnapshot.children) {
                    val movie = movieSnapshot.getValue(MovieDetailData::class.java)
                    movie?.let { movies.add(it) }
                }
                callback(movies)
            }

            override fun onCancelled(e: DatabaseError) {
                Log.d("Retrieve Movies", e.toString())
            }
        })
    }

}