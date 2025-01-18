package com.example.apppantallasnavegacion.Pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pantalla4(navController: NavHostController, param1: String?, param2: String?) {
    Scaffold {
        Column {
            Text("Pantalla 4")
            if (param1 != null && param2 != null) {
                Text("Parámetro 1: $param1")
                Text("Parámetro 2: $param2")
            } else {
                Text("No se recibieron parámetros")
            }
            Button(onClick = {
                navController.navigate("pantalla1") {
                    popUpTo("pantalla1") { inclusive = true }
                }
            }) {
                Text("Volver a Pantalla 1 y borrar pila")
            }
            Button(onClick = { navController.popBackStack("pantalla3", false) }) {
                Text("Volver a Pantalla 3")
            }
        }
    }
}