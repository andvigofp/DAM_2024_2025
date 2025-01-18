package com.example.appcontadores.AppContador.contadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcontadores.AppContador.viewModel.ContadorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContadorPersonalizadoScreen(viewModel: ContadorViewModel, navController: NavController) {
    Column {
        TopAppBar(
            title = { Text("Contador Personalizado") },
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

        var incremento1 by remember { mutableStateOf("") }
        var incremento2 by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Contador 1: $contador1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            TextField(
                value = incremento1,
                onValueChange = { incremento1 = it.filter { char -> char.isDigit() } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Incremento Contador 1") },
                modifier = Modifier.width(200.dp)
            )
            Button(onClick = {
                val incremento = incremento1.toIntOrNull() ?: 1
                viewModel.incrementarContador1(incremento)
            }) {
                Text("Sumar Contador 1")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Contador 2: $contador2", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            TextField(
                value = incremento2,
                onValueChange = { incremento2 = it.filter { char -> char.isDigit() } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Incremento Contador 2") },
                modifier = Modifier.width(200.dp)
            )
            Button(onClick = {
                val incremento = incremento2.toIntOrNull() ?: 1
                viewModel.incrementarContador2(incremento)
            }) {
                Text("Sumar Contador 2")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Contador General: $contadorGeneral", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.reiniciarContadores() }) {
                Text("Reiniciar")
            }
        }
    }
}