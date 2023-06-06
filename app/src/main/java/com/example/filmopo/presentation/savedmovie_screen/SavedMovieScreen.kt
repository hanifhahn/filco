package com.example.filmopo.presentation.savedmovie_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.filmopo.R
import com.example.filmopo.data.api.MovieDetailData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.data.movie_by_user.MovieByUser
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
                        modifier = Modifier.padding(start = 14.dp),
                        color = Color.White,
                        fontSize = 20.sp,
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
        elevation = 4.dp
    ) {
        Row(Modifier.fillMaxWidth().padding(16.dp)) {
            Column(Modifier.weight(1f)) {
                Text(text = movie.Title, style = MaterialTheme.typography.h6)
                Text(text = movie.Year, style = MaterialTheme.typography.body1)
            }

            IconButton(
                onClick = {
                    MovieByUser.deleteMovies(movie.imdbID)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(Icons.Filled.Delete, tint = Color.Red, contentDescription = "Delete")
            }
        }
    }
}
