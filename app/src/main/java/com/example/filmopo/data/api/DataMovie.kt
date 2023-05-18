package com.example.filmopo.data.api

data class DataMovie (
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)

data class Movie(
    val Title: String,
    val Plot: String,
    val Poster: String,
    val Released: String,
    val Rated: String,
    val Genre: String,
    val imdbID: Int
)