package Ej106B;
/**
 * Utilizando la interfaz FilenameFilter realiza los siguientes apartados:
 *
 * Crear una clase FiltrarNombre que implemente el siguiente método:
 *
 * filtrar(String ruta, String extension): lista solo aquellos archivos de la ruta que tienen una determinada extension
 * Crear una clase FiltrarTamano que implemente el siguiente método:
 *
 * filtrar(String ruta, float minTamano): lista solo aquellos archivos de la ruta que tienen un tamaño mayor que el especificado.
 * Realizar un programa main que permita comprobar el funcionamiento de los métodos anteriores
 */
public class Ej106 {
    public static void main(String[] args) {
        System.out.println("Filtrar por nombre");

        //Filtrar por extensión
        new FiltrarNombre();
        FiltrarNombre.filtrar("./", ".txt");

        //Filtrar por tamaño del archivo en bytes
        new FiltrarTamano();
        FiltrarTamano.filtrar("./", 150);
    }
}
