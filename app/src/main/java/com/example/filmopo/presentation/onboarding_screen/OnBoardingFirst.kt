package com.example.filmopo.presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.filmopo.navigation.Screens
import com.example.filmopo.ui.theme.lightBlue
import com.example.filmopo.R
import com.example.filmopo.ui.theme.blue
import com.example.filmopo.ui.theme.deepPurple

@Composable
fun OnBoardingFirst(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.ob1),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                //Trouble organizing a list of movies you want to watch?
                text = "Let's",
                fontSize = 70.sp,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                color = Color.White,
            )

            Text(
                text = "manage your",
                fontSize = 50.sp,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(top = 2.dp),
                color = lightBlue,
            )
            Text(
                text = "watchlist of",
                fontSize = 42.sp,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(top = 12.dp),
                color = Color.White,
            )

            Text(
                text = "Movies",
                fontSize = 85.sp,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                color = Color.White,
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Other Onboarding Content (e.g., additional text, images)

            Spacer(modifier = Modifier.weight(1f))

            // Button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Screens.SignInScreen.route) },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(50), // Mengatur melengkung pada sudut button
                    colors = ButtonDefaults.buttonColors(backgroundColor = lightBlue) // Mengatur warna background button menjadi pink
                ) {
                    Text(text = "Get Started", color = Color.Black, fontSize = 16.sp)
                }

            }
        }
    }
}