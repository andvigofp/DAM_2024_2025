package Ej308;

/**
 * En este ejercicio vamos a desarrollar una pequeña aplicación CRUD (Create, Read, Update, Delete). Es decir, una aplicación que tenga una interfaz gráfica (podéis usar la siguiente plantilla como ejemplo) y nos permita crear, mostrar, actualizar y borrar información de una base de datos. Para ello, vamos a reutilizar el código del ejercicio anterior, en el que trabajábamos con una base de datos llamada school, para que los datos de los estudiantes se muestren en la siguiente interfaz:
 *
 *
 *
 * Debes crear un paquete para la/s clase/s de la intefaz y otro para la lógica de la aplicación. Para la interfaz, puedes utilizar la clase StudentsView que se proporciona junto con el enunciado. Recuerda gestionar los errores, de forma que si hay algún problema al utilizar la base de datos, se le muestre un mensaje al usuario.
 */
public class Main {
    public static void main(String[] args) {
        // Modelo
        Modelo modelo = new Modelo();

        // La Vista
        Vista vista = new Vista();

        // El controlador
        Controlador controlador = new Controlador(modelo, vista);

        // Arranca la interfaz (Vista)
        vista.arrancar();

        // Configura la vista
        vista.setControlador(controlador);

        controlador.actualizarTabla();
    }
}
