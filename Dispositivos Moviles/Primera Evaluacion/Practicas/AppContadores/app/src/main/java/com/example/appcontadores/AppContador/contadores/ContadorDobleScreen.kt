package com.example.appcontadores.AppContador.contadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcontadores.AppContador.viewModel.ContadorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContadorDobleScreen(viewModel: ContadorViewModel, navController: NavController) {
    Column {
        TopAppBar(
            title = { Text("Contador Doble") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )

        // Contenido de la pantalla
        val contador1 by viewModel.contador1.collectAsState()
        val contador2 by viewModel.contador2.collectAsState()
        val contadorGeneral by viewModel.contadorGeneral.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Contador 1: $contador1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Button(onClick = { viewModel.incrementarContador1() }) {
                Text("Sumar Contador 1")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Contador 2: $contador2", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Button(onClick = { viewModel.incrementarContador2() }) {
                Text("Sumar Contador 2")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Contador General: $contadorGeneral", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.reiniciarContadores() }) {
                Text("Reiniciar")
            }
        }
    }
}