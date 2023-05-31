package com.example.filmopo.data.api

import android.media.Image
import androidx.compose.ui.graphics.vector.ImageVector

data class MovieDetailData(
    val imdbID:String,
    val Title: String,
    val Year: String,
    val Poster: String,
    val Released:String
)

