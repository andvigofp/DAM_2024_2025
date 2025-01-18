package EJ107B;

import java.util.ArrayList;

public class MEJ07 {
    // Calcular la media de cada alumno
    public static void calcularMediaAlumnos(ArrayList<AlumnoB> alumnos) {
        for (AlumnoB alumno : alumnos) {
            int[] notas = alumno.getNotas();
            int suma = 0, modulosCursados = 0;

            for (int nota : notas) {
                if (nota != -1) { // Si no es vacío (-1 representa no cursado)
                    suma += nota;
                    modulosCursados++;
                }
            }

            if (modulosCursados > 0) {
                double media = (double) suma / modulosCursados;
                System.out.println("La media de " + alumno.getNombre() + " es: " + media);
            }
        }
    }

    // Calcular la media de cada módulo
    public static void calcularMediaModulos(ArrayList<AlumnoB> alumnos, String[] modulos) {
        for (int i = 0; i < modulos.length; i++) {
            int suma = 0, alumnosCursaron = 0;

            for (AlumnoB alumno : alumnos) {
                int nota = alumno.getNotas()[i];
                if (nota != -1) {
                    suma += nota;
                    alumnosCursaron++;
                }
            }

            if (alumnosCursaron > 0) {
                double media = (double) suma / alumnosCursaron;
                System.out.println("La media del módulo " + modulos[i] + " es: " + media);
            }
        }
    }

    // Calcular cuántos alumnos aprobaron todos los módulos que cursaron
    public static void calcularAprobadosTodosModulos(ArrayList<AlumnoB> alumnos) {
        int aprobadosTodos = 0;

        for (AlumnoB alumno : alumnos) {
            boolean aproboTodos = true;
            for (int nota : alumno.getNotas()) {
                if (nota != -1 && nota < 5) { // Si cursó el módulo y sacó menos de 5
                    aproboTodos = false;
                    break;
                }
            }

            if (aproboTodos) {
                aprobadosTodos++;
            }
        }

        System.out.println("Número de alumnos que aprobaron todos los módulos: " + aprobadosTodos);
    }

    // Calcular cuántos alumnos no cursaron algún módulo
    public static void calcularAlumnosNoCursaron(ArrayList<AlumnoB> alumnos) {
        int noCursaronAlguno = 0;

        for (AlumnoB alumno : alumnos) {
            boolean noCursaron = false;
            for (int nota : alumno.getNotas()) {
                if (nota == -1) { // Si no cursó algún módulo
                    noCursaron = true;
                    break;
                }
            }

            if (noCursaron) {
                noCursaronAlguno++;
            }
        }

        System.out.println("Número de alumnos que no cursaron algún módulo: " + noCursaronAlguno);
    }

    // Módulo con la nota media más alta
    public static void calcularModuloConMejorMedia(ArrayList<AlumnoB> alumnos, String[] modulos) {
        double maxMedia = -1;
        String mejorModulo = "";

        for (int i = 0; i < modulos.length; i++) {
            int suma = 0, alumnosCursaron = 0;

            for (AlumnoB alumno : alumnos) {
                int nota = alumno.getNotas()[i];
                if (nota != -1) {
                    suma += nota;
                    alumnosCursaron++;
                }
            }

            if (alumnosCursaron > 0) {
                double media = (double) suma / alumnosCursaron;
                if (media > maxMedia) {
                    maxMedia = media;
                    mejorModulo = modulos[i];
                }
            }
        }

        System.out.println("El módulo con la mejor media es " + mejorModulo + " con una media de " + maxMedia);
    }

    // Mejor alumno por cada módulo
    public static void calcularMejorAlumnoPorModulo(ArrayList<AlumnoB> alumnos, String[] modulos) {
        for (int i = 0; i < modulos.length; i++) {
            int mejorNota = -1;
            String mejorAlumno = "";

            for (AlumnoB alumno : alumnos) {
                int nota = alumno.getNotas()[i];
                if (nota > mejorNota) {
                    mejorNota = nota;
                    mejorAlumno = alumno.getNombre();
                }
            }

            System.out.println("El mejor alumno en " + modulos[i] + " es " + mejorAlumno + " con una nota de " + mejorNota);
        }
    }
}
