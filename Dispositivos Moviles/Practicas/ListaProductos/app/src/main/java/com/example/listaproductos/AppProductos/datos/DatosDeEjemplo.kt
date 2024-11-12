package com.example.listaproductos.AppProductos.datos

import com.example.listaproductos.AppProductos.viewModel.ListaCompraViewModel

class DatosDeEjemplo {

    // Método para obtener los productos iniciales
    fun obtenerProductosIniciales(): List<ListaCompraViewModel.Producto> {
        return listOf(
            ListaCompraViewModel.Producto("Manzanas"),
            ListaCompraViewModel.Producto("Pan"),
            ListaCompraViewModel.Producto("Leche")
        )
    }
}