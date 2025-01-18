package com.example.appcalculadora.Operaciones

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


// Evaluar operaciones básicas como suma, resta, multiplicación y división
// Evaluar operaciones matemáticas básicas como suma, resta, multiplicación y división
fun evaluateBasicOperation(expression: String): Double {
    // Usar una simple lógica de evaluación, dividiendo la expresión y operando
    var result = 0.0
    try {
        val regex = Regex("""(\d+\.?\d*)\s*([+\-*/])\s*(\d+\.?\d*)""")
        val match = regex.find(expression)

        if (match != null) {
            val num1 = match.groupValues[1].toDouble()
            val operator = match.groupValues[2]
            val num2 = match.groupValues[3].toDouble()

            result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
                else -> Double.NaN
            }
        }
    } catch (e: Exception) {
        result = Double.NaN
    }

    return result
}