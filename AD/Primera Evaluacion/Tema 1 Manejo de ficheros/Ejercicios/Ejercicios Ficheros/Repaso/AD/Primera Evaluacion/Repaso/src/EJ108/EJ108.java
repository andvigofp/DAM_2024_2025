package EJ108;

/**
 * Desarrolla un programa en Java que permita gestionar los libros de un sistema bibliotecario.
 *
 * Supongamos que necesitas desarrollar un sistema de biblioteca que incluye libros y revistas. Cada libro o revista tiene un título y un número de identificación único. Además, se quiere llevar un registro de los préstamos y devoluciones de estos materiales.
 *
 * Para ello utilizaremos herencia y polimorfismo:
 *
 * Crearemos una clase Material que contendrá los atributos y métodos comunes.
 *
 * De la clase Material extenderán las clases Libro y Revista.
 *
 * Los Libros además del título y el número de identificación queremos almacenar el nombre del autor.
 *
 * Para las Revistas queremos almacenar a mayores el número de la revista.
 *
 * Se creará una clase Biblioteca que permita:
 *
 * almacenar el catálogo de libros que tiene la biblioteca disponibles y los libros que están siendo prestados.
 * prestar libros
 * ver los libros prestados
 * devolver los libros prestados
 * Se creará una clase SistemaBiblioteca que contendrá el método main en el que:
 *
 * Creará un par de libros y revistas
 * Los añadirá a la biblioteca
 * Simulará el préstamo de algún libro/revista
 * Simulará la devolución de esos libros/revistas
 */

public class EJ108 {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Crear libros y revistas
        Libro libro1 = new Libro("Java Programming", "001", "John Doe");
        Libro libro2 = new Libro("Data Structures", "002", "Jane Smith");
        Revista revista1 = new Revista("Tech Weekly", "003", 10);
        Revista revista2 = new Revista("Science Monthly", "004", 5);

        // Agregar materiales a la biblioteca
        biblioteca.agregarMaterial(libro1);
        biblioteca.agregarMaterial(libro2);
        biblioteca.agregarMaterial(revista1);
        biblioteca.agregarMaterial(revista2);

        // Mostrar catálogo inicial
        System.out.println("Catálogo inicial:");
        biblioteca.verCatalogo();

        // Simular préstamo
        System.out.println("\nSimulación de préstamo:");
        biblioteca.prestarMaterial("001");
        biblioteca.prestarMaterial("003");

        // Mostrar estado después del préstamo
        System.out.println("\nEstado después del préstamo:");
        biblioteca.verCatalogo();
        biblioteca.verPrestados();

        // Simular devolución
        System.out.println("\nSimulación de devolución:");
        biblioteca.devolverMaterial("001");
        biblioteca.devolverMaterial("003");

        // Mostrar estado después de la devolución
        System.out.println("\nEstado después de la devolución:");
        biblioteca.verCatalogo();
        biblioteca.verPrestados();
    }
}