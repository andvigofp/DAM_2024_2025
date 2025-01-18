package com.example.appcalculadora.Operaciones

// Función para evaluar las expresiones matemáticas
fun eval(expression: String): String {
    return try {
        // Reemplazar operadores para asegurarnos de que la expresión sea válida
        val sanitizedExpression = expression.replace("x", "*")
            .replace("÷", "/")
            .replace("√", "sqrt")
            .replace("ln", "ln")
            .replace("sin", "sin")
            .replace("cos", "cos")
            .replace("tan", "tan")
            .replace("asin", "asin")
            .replace("acos", "acos")
            .replace("atan", "atan")

        // Evaluar la expresión
        evaluateExpression(sanitizedExpression).toString()
    } catch (e: Exception) {
        "Error"
    }
}