package com.example.listaproductos

import android.os.Bundle
import com.example.listaproductos.ui.theme.ListaProductosTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val viewModel: ListaCompraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaProductosTheme {
                ListaCompraApp(viewModel)
            }
        }
    }

    @Composable
    fun ListaCompraApp(viewModel: ListaCompraViewModel) {
        // Observar el estado del ViewModel
        val state by viewModel.state.collectAsState()

        // Snackbar controller
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(
                    onDeleteSelected = {
                        viewModel.eliminarProductosSeleccionados()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Productos eliminados")
                        }
                    },
                    showDeleteButton = state.productosSeleccionados.isNotEmpty() // Solo mostrar si hay productos seleccionados
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputFieldWithAddButton(
                        itemText = "",
                        onTextChange = { },
                        onAddClick = { itemText ->
                            if (itemText.isNotEmpty()) {
                                viewModel.addProducto(itemText)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Lista de productos con opción de eliminar
                    ProductList(
                        listaCompra = state.listaCompra,
                        selectedItems = state.productosSeleccionados,
                        onItemDelete = { item, isChecked ->
                            viewModel.toggleProductoSeleccionado(item, isChecked)
                        }
                    )
                }
            }
        )
    }

    @Composable
    fun TopBar(onDeleteSelected: () -> Unit, showDeleteButton: Boolean) {
        TopAppBar(
            title = { Text("Lista de la compra") },
            actions = {
                if (showDeleteButton) {
                    IconButton(onClick = onDeleteSelected) {
                        Icon(Icons.Filled.Delete, contentDescription = "Eliminar seleccionados")
                    }
                }
            }
        )
    }

    @Composable
    fun InputFieldWithAddButton(
        itemText: String,
        onTextChange: (String) -> Unit,
        onAddClick: (String) -> Unit
    ) {
        var text by remember { mutableStateOf(itemText) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    // Validar que el nuevo texto no contenga números
                    if (newText.all { it.isLetter() || it.isWhitespace() }) {
                        text = newText
                    }
                },
                label = { Text("Añadir producto") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Button(
                onClick = {
                    onAddClick(text)
                    text = "" // Limpiar el campo después de añadir
                }
            ) {
                Text(text = "+")
            }
        }
    }

    @Composable
    fun ProductList(
        listaCompra: List<String>,
        selectedItems: Set<String>,
        onItemDelete: (String, Boolean) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaCompra) { item ->
                ProductListItem(
                    item = item,
                    isChecked = item in selectedItems,
                    onDeleteClick = { isChecked ->
                        onItemDelete(item, isChecked)
                    }
                )
            }
        }
    }

    @Composable
    fun ProductListItem(
        item: String,
        isChecked: Boolean,
        onDeleteClick: (Boolean) -> Unit
    ) {
        var checkboxState by remember { mutableStateOf(isChecked) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto del producto
            Text(
                text = item,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )

            // Checkbox y botón de eliminar
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkboxState,
                    onCheckedChange = {
                        checkboxState = it
                        onDeleteClick(it) // Notificar al callback cuando cambie
                    }
                )

                IconButton(onClick = { onDeleteClick(checkboxState) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar producto"
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ListaCompraApp(viewModel = ListaCompraViewModel())
    }
}