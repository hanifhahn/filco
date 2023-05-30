package com.example.filmopo.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmopo.data.api.MovieDetailData
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.presentation.homepage_screen.DetailScreen
import com.example.filmopo.presentation.homepage_screen.HomepageScreen
import com.example.filmopo.presentation.login_screen.SignInScreen
import com.example.filmopo.presentation.signup_screen.SignUpScreen
import com.example.filmopo.ui.theme.RegularFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    movieViewModel: MovieViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        // Route Sign In
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)
        }
        // Route Sign Up
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        // Route Homepage Screen
        composable(route = Screens.HomepageScreen.route) {
            HomepageScreen(movieViewModel = movieViewModel, navController)
        }

        // Detail screen
        composable(
            route = "${Screens.DetailScreen.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")

            val movieViewModel: MovieViewModel = viewModel()

            val movieData = remember { mutableStateOf<MovieDetailData?>(null) }

            DisposableEffect(movieId) {
                val job = CoroutineScope(Dispatchers.IO).launch {
                    val data = movieViewModel.getMovieById(movieId ?: "")
                    withContext(Dispatchers.Main) {
                        movieData.value = data
                    }
                }

                onDispose {
                    job.cancel()
                }
            }

            movieData.value?.let { movieDetailData ->
                DetailScreen(movieId = movieId!!, movieViewModel = movieViewModel)
            } ?: run {

            }
        }


    }
}
