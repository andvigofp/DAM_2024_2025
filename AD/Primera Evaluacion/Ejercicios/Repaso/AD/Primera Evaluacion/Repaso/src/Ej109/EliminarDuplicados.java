package Ej109;

import java.util.HashSet;

public class EliminarDuplicados {
    // Método para eliminar duplicados y devolver la longitud final
    public static int eliminarDuplicados(int[] array) {
        // Crear un HashSet para almacenar los elementos únicos
        HashSet<Integer> set = new HashSet<>();

        // Agregar cada elemento del array al HashSet
        for (int num : array) {
            set.add(num);
        }

        // La longitud del HashSet es el número de elementos únicos
        return set.size();
    }
}