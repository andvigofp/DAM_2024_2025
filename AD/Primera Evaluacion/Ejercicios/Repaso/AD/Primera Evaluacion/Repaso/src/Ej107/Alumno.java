package Ej107;

import java.util.HashMap;
import java.util.Map;

public class Alumno {
    private String nombre;
    private Map<String, Integer> notas; // Un HashMap para almacenar las notas por módulo
    private int modulosCursados;

    public Alumno(String nombre, int length) {
        this.nombre = nombre;
        this.notas = new HashMap<>();
        this.modulosCursados = 0;
    }

    // Constructor que recibe un arreglo de datos (nombre y notas por módulos)
    public Alumno(String[] datos, String[] modulos) {
        this.nombre = datos[0].trim();
        this.notas = new HashMap<>();
        this.modulosCursados = 0;

        // Asegurarse de que los datos de notas correspondan a los módulos
        for (int i = 1; i < datos.length && i - 1 < modulos.length; i++) {
            String notaString = datos[i].trim();
            if (!notaString.isEmpty()) {
                try {
                    int nota = Integer.parseInt(notaString);
                    notas.put(modulos[i - 1], nota); // Agregar la nota al módulo correspondiente
                    modulosCursados++;
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato de la nota: " + datos[i]);
                }
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota(String modulo) {
        return notas.getOrDefault(modulo, -1); // Retorna la nota o -1 si no hay nota para el módulo
    }

    public void setNota(String modulo, int nota) {
        // Si el módulo aún no tiene una nota registrada, incrementa modulosCursados
        if (!notas.containsKey(modulo)) {
            modulosCursados++;
        }
        notas.put(modulo, nota);
    }

    public double getNotaMedia() {
        int suma = 0;
        int cantidadModulos = 0;

        for (int nota : notas.values()) {
            suma += nota;
            cantidadModulos++;
        }

        return cantidadModulos == 0 ? 0 : (double) suma / cantidadModulos;
    }

    public boolean getAprobadoTodo() {
        for (int nota : notas.values()) {
            if (nota < 5) {
                return false; // Si alguna nota es menor a 5, no ha aprobado todo
            }
        }
        return true; // Si todas las notas son mayores o iguales a 5, ha aprobado todo
    }

    public int getModulosCursados() {
        return modulosCursados;
    }
}