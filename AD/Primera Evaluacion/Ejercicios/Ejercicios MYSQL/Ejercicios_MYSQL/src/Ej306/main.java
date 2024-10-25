package Ej306;

import java.sql.SQLException;

public class main {
    
    public static void main(String[] args) throws SQLException {
        // Modelo
        Modelo modelo = new Modelo();

        // La vista
        Vista vista = new Vista();

        // El controlador
        Controlador controlador = new Controlador(vista, modelo);

        // Arranca la interfaz (Vista)
        vista.arrancar();

        // Configura la vista
        vista.setControlador(controlador);
    }
    
}
