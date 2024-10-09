package Ej122;


public interface Persistencia {
    void escribirPersona(Persona persona, String ruta);
    Persona leerDatos(String ruta);
}
