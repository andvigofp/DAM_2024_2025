package com.example.appcalculadora.calculadora

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

// Función para operaciones básicas como +, -, *, /
fun calculateBasicOperation(expression: String): Double {
    val regex = Regex("([-+*/])")
    val parts = expression.split(regex).filter { it.isNotEmpty() }
    val operators = expression.filter { "+-*/".contains(it) }

    if (parts.size < 2 || operators.isEmpty()) {
        throw IllegalArgumentException("Expresión no válida")
    }

    var result = parts[0].toDouble()
    for (i in 1 until parts.size) {
        val operand = parts[i].toDouble()
        val operator = operators[i - 1]

        result = when (operator) {
            '+' -> result + operand
            '-' -> result - operand
            '*' -> result * operand
            '/' -> {
                if (operand == 0.0) throw ArithmeticException("División por cero")
                result / operand
            }
            else -> throw IllegalArgumentException("Operador no válido")
        }
    }

    return result
}