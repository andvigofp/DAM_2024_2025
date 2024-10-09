package Ej109;

/**
 * Desarrolla un programa en Java que permita eliminar los elementos duplicados de un array y devuelva la longitud final sin los duplicados
 *
 * ejemplo funcionamiento
 * array = [20, 20, 30, 40, 50, 50, 50]
 *
 * Devolverá 4
 */
public class Ej109 {
    public static void main(String[] args) {
        int[] array = {20, 20, 30, 40, 50, 50, 50};

        // Llamar al método de EliminadorDuplicados para obtener la longitud sin duplicados
        int longitudFinal = EliminarDuplicados.eliminarDuplicados(array);

        // Mostrar el resultado
        System.out.println("La longitud final sin duplicados es: " + longitudFinal);
    }
}