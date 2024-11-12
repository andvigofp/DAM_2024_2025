package Ej305;

/**
 * Desarrolla una aplicación, siguiendo el patrón MVC, que sume dos números y muestre el resultado. Para ello puedes utilizar el siguiente proyecto como plantilla.
 *
 * TODO
 * En el proyecto de plantilla hay comentarios marcados con la palabra TODO que indica lo que habría que hacer.
 */

public class main {
    
    public static void main(String[] args) {

        // Modelo
        Modelo modelo = new Modelo();

        // La vista
        Vista vista = new Vista();

        // El controlador
        Controlador controlador = new Controlador(vista, modelo);

        // Arrancar la interfaz (vista)
        vista.arranca();

        // Configura la vista
        vista.setControlador(controlador);
    }
}
