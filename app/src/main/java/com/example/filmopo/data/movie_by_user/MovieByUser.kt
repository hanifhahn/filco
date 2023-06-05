package com.example.filmopo.data.movie_by_user

import android.annotation.SuppressLint
import android.util.Log
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
            @SuppressLint("LongLogTag")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movies = mutableListOf<MovieDetailData>()
                for (movieSnapshot in dataSnapshot.children) {
                    val movieByUserId = movieSnapshot.key.toString()
                    val movieByUserIdUse = movieByUserId.split("-")
                    val movieByUserIdUseFirstSubstring = movieByUserIdUse[0]
                    Log.d("retrieveMovies movieByUserIdUseFirstSubstring", movieByUserIdUseFirstSubstring)

                    if (uid == movieByUserIdUseFirstSubstring) {
                        val movie = movieSnapshot.getValue(MovieDetailData::class.java)
                        movie?.let { movies.add(it) }
                        Log.d("Retrieve Movies", "Saved Movie Found")
                    } else {
                        Log.d("Retrieve Movies", "Saved Movie Not Found")
                    }
                }
                callback(movies)
            }

            override fun onCancelled(e: DatabaseError) {
                Log.d("Retrieve Movies", e.toString())
            }
        })
    }

    @SuppressLint("LongLogTag")
    fun deleteMovies(imdbID: String) {
        val movieByUserId = "$uid-$imdbID"
        Log.d("deleteMovies ID", imdbID)
        Log.d("deleteMovies movieByUserId", movieByUserId)
        val moviesDeleteRef = moviesRef.child(movieByUserId)
        moviesDeleteRef.removeValue()
    }

}