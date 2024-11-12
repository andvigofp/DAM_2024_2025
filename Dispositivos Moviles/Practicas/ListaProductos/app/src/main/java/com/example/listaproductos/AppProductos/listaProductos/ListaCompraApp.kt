package com.example.listaproductos.AppProductos.listaProductos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listaproductos.AppProductos.viewModel.ListaCompraViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompraApp(viewModel: ListaCompraViewModel) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() } // Usamos SnackbarHostState para manejar los mensajes
    val scope = rememberCoroutineScope()

    // Scaffold de Material3 con SnackbarHostState
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de la compra") },
                actions = {
                    IconButton(onClick = {
                        // Verificar si hay productos marcados para eliminar
                        val productosMarcados = state.listaCompra.any { it.isChecked }
                        if (productosMarcados) {
                            viewModel.eliminarProductosRealizados()
                            // Mostrar Snackbar después de eliminar productos
                            scope.launch {
                                snackbarHostState.showSnackbar("Productos eliminados correctamente.")
                            }
                        } else {
                            // Mostrar el Snackbar si no hay productos marcados
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Por favor, marque el checkbox antes de borrar"
                                )
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar realizados")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }, // Añadir el SnackbarHost al Scaffold
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Campo de texto y botón para añadir productos
                InputFieldWithAddButton(viewModel)

                Spacer(modifier = Modifier.height(16.dp))

                // Mostrar la lista de productos
                LazyColumn {
                    items(state.listaCompra) { item ->
                        ProductListItem(
                            item = item.nombre,
                            isChecked = item.isChecked,
                            onCheckedChange = { isChecked ->
                                viewModel.toggleProductoRealizado(item.nombre, isChecked)
                            },
                            onDeleteClick = {
                                // Verificar si el checkbox está marcado antes de borrar
                                if (item.isChecked) {
                                    viewModel.eliminarProducto(item.nombre)

                                    // Mostrar Snackbar después de eliminar productos
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Productos eliminados correctamente.")
                                    }
                                } else {
                                    // Mostrar el Snackbar si no está marcado
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "Por favor, marque el checkbox antes de borrar"
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}