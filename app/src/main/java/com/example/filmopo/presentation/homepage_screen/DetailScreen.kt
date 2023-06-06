package com.example.filmopo.presentation.homepage_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.filmopo.data.api.MovieDetailData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.data.movie_by_user.MovieByUser
import com.example.filmopo.navigation.Screens
import com.example.filmopo.ui.theme.lightBlue
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

    LazyColumn(

        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (movieData != null) {
            movieData?.let {
                item {

                    MoviePoster(it.Poster, Modifier.width(410.dp).aspectRatio(2f))

                    Text(
                        text = it.Title,
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 25.sp
                        ),
                        modifier = Modifier
                            .padding(18.dp)
                            .offset(y = (250).dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                            .offset(y = (230).dp)
                    ) {
                        Box(

                        ) {
                            Box(
                                modifier = Modifier
                                    .background(lightBlue, shape = RoundedCornerShape(20.dp))
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = it.Year,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .offset(x = (15).dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(lightBlue, shape = RoundedCornerShape(20.dp))
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = it.Released,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }

                        }

                        Box(
                            modifier = Modifier
                                .offset(x = (30).dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(lightBlue, shape = RoundedCornerShape(20.dp))
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = it.Rated,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }

                        }

                    }


                    Text(
                        text = "Plot:",
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .offset(y = (230).dp),
                    )

                    Text(
                        text = it.Plot,
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .offset(y = (210).dp),
                    )

                }
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
                MovieByUser.createMovie(movieData)
                Toast.makeText(context, "Movie Added", Toast.LENGTH_LONG).show()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            modifier = Modifier
                .width(170.dp)
                .height(50.dp)
                .offset(y = (750).dp),
            shape = RoundedCornerShape(50),
        ) {
            Text(
                text = "Add Movie",
                color = Color.White,
                fontSize = 14.sp
            )
            Log.d("Hasil List Detail Movie", movieData.toString())
        }
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MoviePoster(poster: String, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 50.dp,
            bottomEnd = 50.dp
        ),
        backgroundColor = Color.Black,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 18f)
            .offset(y = (120).dp) // Menggeser konten ke atas
    ) {
        GlideImage(
            model = poster,
            contentDescription = "Movie poster",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(16f / 22f)
                .offset(y = (-40).dp),// Menggeser konten ke atas
            contentScale = ContentScale.Crop
        )
    }
}



