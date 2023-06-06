package com.example.filmopo.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens(route = "SplashScreen")
    object OnBoardingFirst : Screens(route = "OnBoardingFirst")
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object HomepageScreen : Screens(route = "HomepageScreen")
    object DetailScreen : Screens(route = "DetailScreen")
    object SavedMovieScreen : Screens(route = "SavedMovieScreen")
}
