package com.example.appcontadores.AppContador.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ContadorViewModel:ViewModel() {
    // Definimos los contadores como StateFlow para que se puedan observar desde la UI
    private val _contador1 = MutableStateFlow(0)
    val contador1: StateFlow<Int> = _contador1

    private val _contador2 = MutableStateFlow(0)
    val contador2: StateFlow<Int> = _contador2

    private val _contadorGeneral = MutableStateFlow(0)
    val contadorGeneral: StateFlow<Int> = _contadorGeneral

    // Funciones para incrementar los contadores
    fun incrementarContador1(incremento: Int = 1) {
        _contador1.update { it + incremento }
        _contadorGeneral.update { it + incremento }
    }

    fun incrementarContador2(incremento: Int = 1) {
        _contador2.update { it + incremento }
        _contadorGeneral.update { it + incremento }
    }

    // Función para reiniciar los contadores
    fun reiniciarContadores() {
        _contador1.value = 0
        _contador2.value = 0
        _contadorGeneral.value = 0
    }

}