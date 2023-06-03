package com.example.filmopo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmopo.navigation.Screens

@Composable
fun BottomBarApp(
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        contentAlignment = androidx.compose.ui.Alignment.BottomEnd,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {

        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.SavedMovieScreen.route)
            },
            modifier = androidx.compose.ui.Modifier.offset(
                x = (-16).dp,
                y = (-26).dp
            ),
            backgroundColor = Color.Blue,
            contentColor = Color.White
        ) {
            Icon(Icons.Filled.List, contentDescription = "Add")
        }

    }
}