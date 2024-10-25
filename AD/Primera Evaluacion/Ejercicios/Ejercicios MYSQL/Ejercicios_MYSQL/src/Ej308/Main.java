package Ej308;

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
