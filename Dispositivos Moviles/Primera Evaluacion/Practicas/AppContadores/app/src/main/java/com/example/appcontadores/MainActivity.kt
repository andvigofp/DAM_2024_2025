package com.example.appcontadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcontadores.AppContador.contadores.ContadorDobleScreen
import com.example.appcontadores.AppContador.contadores.ContadorPersonalizadoScreen
import com.example.appcontadores.AppContador.contadores.ContadorSimpleScreen
import com.example.appcontadores.AppContador.viewModel.ContadorViewModel


class MainActivity : ComponentActivity() {
    private val contadorViewModel: ContadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(contadorViewModel)
        }
    }
}

@Composable
fun MainApp(contadorViewModel: ContadorViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("contadorSimple") { ContadorSimpleScreen(viewModel = contadorViewModel, navController = navController) }
        composable("contadorDoble") { ContadorDobleScreen(viewModel = contadorViewModel, navController = navController) }
        composable("contadorPersonalizado") { ContadorPersonalizadoScreen(viewModel = contadorViewModel, navController = navController) }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("contadorSimple") }) {
            Text("Contador Simple")
        }
        Button(onClick = { navController.navigate("contadorDoble") }) {
            Text("Contador Doble")
        }
        Button(onClick = { navController.navigate("contadorPersonalizado") }) {
            Text("Contador Personalizado")
        }
    }
}


