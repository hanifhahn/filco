package com.example.filmopo.presentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.filmopo.R
import com.example.filmopo.navigation.Screens
import com.example.filmopo.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Surface(color = lightBlue) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = buildAnnotatedStringForMima(),
                fontFamily = RegularFont,
                fontWeight = FontWeight.Bold,
                fontSize = 37.sp,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
            )
        }
        LaunchedEffect(Unit) {
            // Delay for 3 seconds
            delay(3000)

            // Navigate to SignInScreen
            navController.navigate(Screens.OnBoardingFirst.route)
        }
    }
}

@Composable
fun buildAnnotatedStringForMima(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = deepPurple)) {
            append("FiL")
        }
        withStyle(style = SpanStyle(color = blue)) {
            append("Co")
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = NavController(LocalContext.current))
}