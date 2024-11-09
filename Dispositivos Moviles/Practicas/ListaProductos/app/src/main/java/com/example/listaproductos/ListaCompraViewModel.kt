package com.example.listaproductos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListaCompraViewModel : ViewModel() {

    // Estado que contiene la lista de productos y los elementos seleccionados
    private val _state = MutableStateFlow(ListaCompraState())
    val state: StateFlow<ListaCompraState> get() = _state

    // Función para agregar un producto
    fun addProducto(producto: String) {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra + producto
        )
    }

    // Función para actualizar los productos seleccionados
    fun toggleProductoSeleccionado(producto: String, isChecked: Boolean) {
        val nuevosSeleccionados = if (isChecked) {
            _state.value.productosSeleccionados + producto
        } else {
            _state.value.productosSeleccionados - producto
        }
        _state.value = _state.value.copy(productosSeleccionados = nuevosSeleccionados)
    }

    // Función para eliminar los productos seleccionados
    fun eliminarProductosSeleccionados() {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra.filterNot { it in _state.value.productosSeleccionados },
            productosSeleccionados = emptySet()
        )
    }
}

// Estado que contiene la lista de productos y los elementos seleccionados
data class ListaCompraState(
    val listaCompra: List<String> = listOf(),
    val productosSeleccionados: Set<String> = emptySet()
)