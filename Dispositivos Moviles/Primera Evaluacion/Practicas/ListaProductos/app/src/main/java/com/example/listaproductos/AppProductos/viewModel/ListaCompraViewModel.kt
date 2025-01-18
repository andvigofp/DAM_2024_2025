package com.example.listaproductos.AppProductos.viewModel

import androidx.lifecycle.ViewModel
import com.example.listaproductos.AppProductos.datos.DatosDeEjemplo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListaCompraViewModel : ViewModel() {

    // Estado que contiene la lista de productos y los elementos seleccionados
    private val _state = MutableStateFlow(ListaCompraState())
    val state: StateFlow<ListaCompraState> get() = _state

    // Instancia de la clase que contiene los datos de ejemplo
    private val datosDeEjemplo = DatosDeEjemplo()

    // Bloque de inicialización para agregar datos de ejemplo al iniciar la app
    init {
        _state.value = _state.value.copy(
            listaCompra = datosDeEjemplo.obtenerProductosIniciales()
        )
    }

    // Función para agregar un producto
    fun addProducto(producto: String) {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra + Producto(producto)
        )
    }

    // Función para eliminar un producto
    fun eliminarProducto(producto: String) {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra.filter { it.nombre != producto }
        )
    }

    // Función para eliminar productos marcados como realizados
    fun eliminarProductosRealizados() {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra.filterNot { it.isChecked }
        )
    }

    // Función para cambiar el estado de realizado/no realizado
    fun toggleProductoRealizado(producto: String, isChecked: Boolean) {
        _state.value = _state.value.copy(
            listaCompra = _state.value.listaCompra.map {
                if (it.nombre == producto) it.copy(isChecked = isChecked) else it
            }
        )
    }

    // Estado que contiene la lista de productos y los elementos seleccionados
    data class ListaCompraState(
        val listaCompra: List<Producto> = listOf()
    )

    data class Producto(
        val nombre: String,
        val isChecked: Boolean = false
    )
}