package com.example.filmopo.presentation.homepage_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.filmopo.data.api.MovieData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.navigation.Screens
import com.example.filmopo.presentation.BottomBarApp
import com.example.filmopo.presentation.TopBarApp
import com.example.filmopo.ui.theme.lightBlue
import kotlinx.coroutines.launch

@Composable
fun HomepageScreen(
    movieViewModel: MovieViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = { TopBarApp(navController = navController) },
        content = {
            Column(Modifier.padding(8.dp)) {
                SearchBar(movieViewModel)
                MovieList(movieViewModel, navController)
            }
        },
        bottomBar = { BottomBarApp(navController = navController) }
    )
}

@Composable
fun SearchBar(movieViewModel: MovieViewModel) {
    var cari by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
        TextField(
            value = cari,
            onValueChange = { cari = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(end = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = { Text(text = "Search Title Movie...") }
        )

        Button(
            onClick = {
                scope.launch {
                    movieViewModel.searchMovies(cari, context)
                }
            },
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Cari", color = Color.White, modifier = Modifier.padding(3.dp))
        }
    }
}

@Composable
fun MovieList(movieViewModel: MovieViewModel, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)) {
        itemsIndexed(items = movieViewModel.state.value) { index, movieData ->
            MovieItem(item = movieData, navController = navController)
        }
    }
}

@Composable
fun MovieItem(item: MovieData, navController: NavController) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("${Screens.DetailScreen.route}/${item.imdbID}")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            MoviePoster(item.Poster, Modifier.weight(0.40f))
            MovieDetails(item.Title, item.Year, Modifier.weight(0.60f), navController)
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
private fun MovieDetails(
    title: String,
    year: String,
    modifier: Modifier,
    navController: NavController
) {
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





