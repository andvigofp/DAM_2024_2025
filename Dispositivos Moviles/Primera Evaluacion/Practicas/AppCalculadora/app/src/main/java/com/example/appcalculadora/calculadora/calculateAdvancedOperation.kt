package com.example.appcalculadora.calculadora

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

// Función para operaciones avanzadas y trigonométricas
fun calculateAdvancedOperation(value: Double, operation: String): Double {
    return try {
        when (operation) {
            "sin" -> sin(Math.toRadians(value)) // Convertir a radianes
            "cos" -> cos(Math.toRadians(value)) // Convertir a radianes
            "tan" -> tan(Math.toRadians(value)) // Convertir a radianes
            "asin" -> Math.toDegrees(asin(value)) // Convertir a grados
            "acos" -> Math.toDegrees(acos(value)) // Convertir a grados
            "atan" -> Math.toDegrees(atan(value)) // Convertir a grados
            "√" -> sqrt(value)
            "ln" -> ln(value)
            else -> throw IllegalArgumentException("Operación no válida")
        }
    } catch (e: Exception) {
        throw IllegalArgumentException("Error en la operación avanzada: ${e.message}")
    }
}