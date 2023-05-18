package com.example.filmopo.presentation.homepage_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.filmopo.data.api.Movie
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.presentation.TopBarApp

@Composable
fun HomepageScreen(
    movieViewModel: MovieViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = { TopBarApp(navController = navController) },
        content = {
            Column {
                Spacer(modifier = Modifier.height(15.dp))
                Detaillist(
                    movie = movieViewModel.listMovie,
                    navController = navController,
                    movieViewModel = movieViewModel
                )
            }
        }
    )
}

@Composable
fun Detaillist(movie: List<Movie>, navController: NavController, movieViewModel: MovieViewModel) {

    if (movie.isNotEmpty()) {
        LazyColumn(content = {
            itemsIndexed(items = movie) { _, item ->
                DetailCard(
                    data = item,
                    navController = navController,
                    movieViewModel = movieViewModel
                )
            }
        })
    }
}

@Composable
fun DetailCard(data: Movie, navController: NavController, movieViewModel: MovieViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                movieViewModel.getDetailMovie(data.imdbID)
                navController.navigate("DetailScreen/${data.imdbID}")
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(data.Poster),
                    contentDescription = data.Title,
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 400f
                    )
                )
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = data.Title,style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}
