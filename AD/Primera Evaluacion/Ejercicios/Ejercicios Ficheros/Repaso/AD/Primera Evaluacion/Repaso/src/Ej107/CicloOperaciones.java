package Ej107;

import java.util.ArrayList;
import java.util.Scanner;

public class CicloOperaciones {
    // Método para imprimir el menú
    public void Menu() {
        System.out.println("1. Agregar un nuevo alumno.");
        System.out.println("2. Mostrar la nota media de cada alumno.");
        System.out.println("3. Calcular la media de cada módulo.");
        System.out.println("4. Mostrar alumnos que aprobaron todos los módulos.");
        System.out.println("5. Calcular el número de alumnos que no han cursado todos los módulos.");
        System.out.println("6. Mostrar el módulo con la media más alta.");
        System.out.println("7. Mostrar el alumno con la mejor nota por módulo.");
        System.out.println("8. Salir.");
    }

    // Método para agregar un nuevo alumno
    public void agregarAlumno(Scanner sc, ArrayList<Alumno> alumnos, String[] materias) {
        System.out.println("Introduce el nombre del nuevo alumno:");
        String nombre = sc.nextLine().trim();

        Alumno nuevoAlumno = new Alumno(nombre, materias.length);

        for (int i = 0; i < materias.length; i++) {
            System.out.println("Introduce la nota del alumno en " + materias[i] + " (deja en blanco si no cursó el módulo):");
            String inputNota = sc.nextLine().trim();

            if (!inputNota.isBlank()) {
                try {
                    int nota = Integer.parseInt(inputNota);
                    nuevoAlumno.setNota(materias[i], nota);
                } catch (NumberFormatException e) {
                    System.out.println("Nota inválida. No se agregará ninguna nota para este módulo.");
                }
            }
        }

        alumnos.add(nuevoAlumno);
        System.out.println("Alumno agregado exitosamente.");
    }

    // Método para calcular la media de cada alumno
    public void notaMediaAlumno(ArrayList<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println("La nota media de " + alumno.getNombre() + " es: " + alumno.getNotaMedia());
        }
    }

    // Método para calcular la media de cada módulo
    public void notaMediaModulo(ArrayList<Alumno> alumnos, String[] materias) {
        for (String modulo : materias) {
            double sumaNotas = 0;
            int numAlumnos = 0;

            for (Alumno alumno : alumnos) {
                int nota = alumno.getNota(modulo);
                if (nota > 0) {
                    sumaNotas += nota;
                    numAlumnos++;
                }
            }

            double media = (numAlumnos == 0) ? 0 : sumaNotas / numAlumnos;
            System.out.println("La media del módulo " + modulo + " es: " + media);
        }
    }

    // Método para calcular la nota media más alta entre módulos
    public void notaMediaMasAlta(ArrayList<Alumno> alumnos, String[] materias) {
        String mejorModulo = "";
        double mejorMedia = 0;

        for (String modulo : materias) {
            double sumaNotas = 0;
            int numAlumnos = 0;

            for (Alumno alumno : alumnos) {
                int nota = alumno.getNota(modulo);
                if (nota > 0) {
                    sumaNotas += nota;
                    numAlumnos++;
                }
            }

            double media = (numAlumnos == 0) ? 0 : sumaNotas / numAlumnos;
            if (media > mejorMedia) {
                mejorMedia = media;
                mejorModulo = modulo;
            }
        }

        System.out.println("El módulo con la media más alta es " + mejorModulo + " con una media de: " + mejorMedia);
    }

    // Método para mostrar el mejor alumno por cada módulo
    public void alumnoMejorNotaPorModulo(ArrayList<Alumno> alumnos, String[] materias) {
        for (String modulo : materias) {
            Alumno mejorAlumno = null;
            int mejorNota = -1;

            for (Alumno alumno : alumnos) {
                int nota = alumno.getNota(modulo);
                if (nota > mejorNota) {
                    mejorNota = nota;
                    mejorAlumno = alumno;
                }
            }

            if (mejorAlumno != null) {
                System.out.println("El mejor alumno en " + modulo + " es " + mejorAlumno.getNombre() + " con una nota de " + mejorNota);
            }
        }
    }

    // Método para mostrar los alumnos que aprobaron todos los módulos
    public void alumnosAprobaronTodo(ArrayList<Alumno> alumnos) {
        System.out.println("Alumnos que aprobaron todos los módulos:");
        for (Alumno alumno : alumnos) {
            if (alumno.getAprobadoTodo()) {
                System.out.println(alumno.getNombre());
            }
        }
    }

    // Método para calcular cuántos alumnos no han cursado todos los módulos
    public void alumnosNoCursaronTodo(ArrayList<Alumno> alumnos, int totalModulos) {
        int numAlumnos = 0;
        for (Alumno alumno : alumnos) {
            if (alumno.getModulosCursados() < totalModulos) {
                numAlumnos++;
            }
        }
        System.out.println("El número de alumnos que no han cursado todos los módulos es: " + numAlumnos);
    }
}