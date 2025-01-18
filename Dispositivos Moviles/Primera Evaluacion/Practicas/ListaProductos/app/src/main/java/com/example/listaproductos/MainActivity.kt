package com.example.listaproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.listaproductos.AppProductos.listaProductos.ListaCompraApp
import com.example.listaproductos.AppProductos.viewModel.ListaCompraViewModel
import com.example.listaproductos.ui.theme.ListaProductosTheme

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
}




