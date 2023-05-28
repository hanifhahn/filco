package com.example.filmopo.presentation.homepage_screen

import android.graphics.Movie
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.filmopo.data.api.MovieData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.presentation.TopBarApp
import com.example.filmopo.ui.theme.FILMOPOTheme

@Composable
fun HomepageScreen(
    //movieViewModel: MovieViewModel,
    navController: NavController
) {
    //val movieData = MovieData(Title = "", Year = "")

    val viewModel: MovieViewModel = viewModel()

    Scaffold(
        topBar = { TopBarApp(navController = navController) },

        content = {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    )
                ){
                    itemsIndexed(items = viewModel.state.value) { index, movieData ->
                        MovieItem(item = movieData)
                    }
                }
//                Spacer(modifier = Modifier.height(15.dp))
//
//                viewModel.state.value.forEach { movieData ->
//                    MovieItem(item = movieData)
//                }
            }
        }
    )
}

@Composable
fun MovieItem(item: MovieData) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            MoviePoster(item.Poster, Modifier.weight(0.40f))
            MovieDetails(item.Title, item.Year, Modifier.weight(0.60f))
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

@Composable
private fun MovieDetails(title: String, year: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = year,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

//@Composable
//fun DetailCard(data: MovieData, movieViewModel: MovieViewModel) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp)
//            .clickable {
//                movieViewModel.getDetailMovie(data.imdbID)
//               // navController.navigate("DetailScreen/${data.imdbID}")
//            },
//        shape = RoundedCornerShape(15.dp),
//        elevation = 5.dp,
//    ) {
//        Box(
//            modifier = Modifier
//                .height(200.dp)
//        ) {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Image(
//                    painter = rememberImagePainter(data.Poster),
//                    contentDescription = data.Title,
//                    contentScale = ContentScale.FillWidth
//                )
//            }
//            Box(modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.verticalGradient(
//                        listOf(
//                            Color.Transparent,
//                            Color.Black
//                        ),
//                        startY = 400f
//                    )
//                )
//            )
//            Box(modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//                contentAlignment = Alignment.BottomStart
//            ) {
//                Text(text = data.Title,style = TextStyle(color = Color.White, fontSize = 16.sp))
//            }
//        }
//    }
//}

