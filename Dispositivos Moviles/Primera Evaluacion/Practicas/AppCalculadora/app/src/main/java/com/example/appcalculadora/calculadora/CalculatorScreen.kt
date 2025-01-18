package com.example.appcalculadora.calculadora

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcalculadora.Operaciones.eval


@Composable
fun CalculatorScreen() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Lista de botones para operaciones básicas y números
    val buttons = listOf(
        "7", "8", "9", "÷",
        "4", "5", "6", "x",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    )

    // Fila para los botones de operaciones básicas y números
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Muestra el resultado y la entrada
        Text(text = "Resultado: $result", fontSize = 32.sp, modifier = Modifier.fillMaxWidth())
        Text(text = "Entrada: $input", fontSize = 24.sp, modifier = Modifier.fillMaxWidth())

        // Fila para los botones de operaciones básicas y números
        for (i in buttons.chunked(4)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                i.forEach { label ->
                    Button(
                        onClick = {
                            if (label == "=") {
                                result = eval(input)
                            } else {
                                input += label
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = label)
                    }
                }
            }
        }

        // Fila para botones de operaciones avanzadas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("sin", "cos", "tan", "ln", "√").forEach { label ->
                Button(
                    onClick = { input += "$label(" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = label)
                }
            }
        }

        // Fila para botones de operaciones trigonométricas inversas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("asin", "acos", "atan").forEach { label ->
                Button(
                    onClick = { input += "$label(" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = label)
                }
            }
        }

        // Botón AC centrado y más pequeño
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    input = ""
                    result = ""
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
            ) {
                Text(text = "AC")
            }
        }
    }
}