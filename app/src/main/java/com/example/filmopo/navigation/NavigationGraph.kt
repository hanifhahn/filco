package com.example.filmopo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmopo.data.api.MovieViewModel
import com.example.filmopo.presentation.homepage_screen.DetailScreen
import com.example.filmopo.presentation.homepage_screen.HomepageScreen
import com.example.filmopo.presentation.login_screen.SignInScreen
import com.example.filmopo.presentation.signup_screen.SignUpScreen

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
            HomepageScreen(movieViewModel = movieViewModel, navController = navController)
        }

        // Detail screen belum
    }
}