package com.example.apppantallasnavegacion.Pantallas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pantalla1") {
        composable("pantalla1") { Pantalla1(navController) }
        composable("pantalla2") { Pantalla2(navController) }
        composable("pantalla3") { Pantalla3(navController) }
        composable("pantalla4/{param1}/{param2}") { backStackEntry ->
            val param1 = backStackEntry.arguments?.getString("param1")
            val param2 = backStackEntry.arguments?.getString("param2")
            Pantalla4(navController, param1, param2)
        }
        composable("pantalla4") { Pantalla4(navController, null, null) }
    }
}