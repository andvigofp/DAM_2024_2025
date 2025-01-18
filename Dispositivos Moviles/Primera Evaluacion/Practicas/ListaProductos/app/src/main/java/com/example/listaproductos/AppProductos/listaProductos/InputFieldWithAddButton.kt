package com.example.listaproductos.AppProductos.listaProductos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listaproductos.AppProductos.viewModel.ListaCompraViewModel

@Composable
fun InputFieldWithAddButton(viewModel: ListaCompraViewModel) {
    var itemText by remember { mutableStateOf("") }

    // Función para verificar si el texto solo contiene letras
    fun isTextValid(text: String): Boolean {
        // Validar que el texto contiene solo letras (sin números ni espacios)
        return text.all { it.isLetter() } // Solo letras
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = itemText,
            onValueChange = { newText ->
                // Solo actualizar el texto si es válido (solo letras)
                if (isTextValid(newText)) {
                    itemText = newText
                }
            },
            label = { Text("Añadir producto") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                if (itemText.isNotBlank()) {
                    viewModel.addProducto(itemText)
                    itemText = "" // Limpiar el campo después de agregar
                }
            }
        ) {
            Text("+")
        }
    }
}