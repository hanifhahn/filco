package com.example.filmopo.presentation.savedmovie_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmopo.R
import com.example.filmopo.data.api.MovieDetailData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.data.movie_by_user.MovieByUser.retrieveMovies

@Composable
fun SavedMovieScreen(
    movieViewModel: MovieViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.White
                    )
                    Text(
                        text = "Saved Movie",
                        modifier = Modifier.padding(end = 8.dp),
                        color = Color.White
                    )
                }
            }
        },
        content = {
            MovieListScreen()
        }
    )
}

@Composable
fun MovieListScreen() {
    val moviesState: MutableState<List<MovieDetailData>> = remember { mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
        retrieveMovies { movies ->
            moviesState.value = movies
        }
    }

    Scaffold(
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                for (movie in moviesState.value) {
                    MovieItem(movie)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun MovieItem(movie: MovieDetailData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = movie.Title, style = MaterialTheme.typography.h6)
                Text(text = movie.Year, style = MaterialTheme.typography.body1)
            }
        }
    )
}