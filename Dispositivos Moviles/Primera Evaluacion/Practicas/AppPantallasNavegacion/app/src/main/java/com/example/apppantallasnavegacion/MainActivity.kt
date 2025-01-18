package com.example.apppantallasnavegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppantallasnavegacion.Pantallas.MainApp
import com.example.apppantallasnavegacion.Pantallas.Pantalla1
import com.example.apppantallasnavegacion.Pantallas.Pantalla2
import com.example.apppantallasnavegacion.Pantallas.Pantalla3
import com.example.apppantallasnavegacion.Pantallas.Pantalla4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}


