package com.example.filmopo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.filmopo.navigation.NavigationGraph
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance("https://filmopo-575ad-default-rtdb.asia-southeast1.firebasedatabase.app").setPersistenceEnabled(true)

        setContent {
            NavigationGraph()
        }
    }
}
