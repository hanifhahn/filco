package com.example.filmopo.presentation.homepage_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import coil.compose.rememberImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.filmopo.data.api.MovieData
import com.example.filmopo.data.api.MovieDetailData
import com.example.filmopo.data.api.MovieViewModel
import kotlinx.coroutines.*


@Composable
fun DetailScreen(
    movieId: String,
    movieViewModel: MovieViewModel,
) {
    var movieData by remember { mutableStateOf<MovieDetailData?>(null) }

    LaunchedEffect(movieId) {
        val data = withContext(Dispatchers.IO) {
            movieViewModel.getMovieById(movieId)
        }
        movieData = data
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (movieData != null) {
            movieData?.let {
                MoviePoster(it.Poster, Modifier.weight(0.40f))
                Text(
                    text = it.Title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = it.Year,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Released: ${it.Released}",
                    style = MaterialTheme.typography.body2
                )
            }
        } else {
            LaunchedEffect(Unit) {
                // Delay the display of "Movie Not Found" text for a short duration
                delay(1000)
                movieData = MovieDetailData("", "", "", "") // Set empty data to hide the text
            }

        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MoviePoster(poster: String, modifier: Modifier) {
    GlideImage(
        model = poster,
        contentDescription = "Movie poster",
        modifier = modifier.padding(8.dp),
        contentScale = ContentScale.FillBounds
    )
}

