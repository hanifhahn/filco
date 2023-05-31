package com.example.filmopo.presentation

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.filmopo.R
import com.example.filmopo.navigation.Screens
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun TopBarApp(
    navController: NavController
) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            Button(
                onClick = {
                    Firebase.auth.signOut()
                    navController.navigate(Screens.SignInScreen.route)
                    Toast.makeText(context, "Logout Success", Toast.LENGTH_LONG).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            )
            {
                Text(text = "Logout")
            }
//            IconButton(
//                onClick = {
//                    Firebase.auth.signOut()
//                    navController.navigate(Screens.SignInScreen.route)
//                    Toast.makeText(context, "Logout Success", Toast.LENGTH_LONG).show()
//                }
//            ) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Logout")
//            }
        }
    )
}