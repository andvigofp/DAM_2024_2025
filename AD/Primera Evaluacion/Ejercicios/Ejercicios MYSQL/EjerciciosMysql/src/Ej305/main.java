package Ej305;

public class main {
    
    public static void main(String[] args) {
        
     // Módelo
     Modelo modelo = new Modelo();

     // La Vista
     Vista vista = new Vista();

     // El controlador
     Controlador controlador = new Controlador(vista, modelo);

     // Arrancar la interfaz (vista)
      vista.arranca();

      //Configura la vista
        vista.setControlador(controlador);
        
    }
}
