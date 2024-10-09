package EJ107B;

import java.util.ArrayList;
import java.util.Scanner;

public class EJ107B {
    public static void main(String[] args) {
        // Definir módulos
        String[] modulos = {"LMSXI", "SI", "BD", "PRO", "CD", "FOL"};

        // Crear alumnos y notas (-1 representa que no cursó el módulo)
        AlumnoB alumno1 = new AlumnoB("Alumno 1", new int[]{4, -1, 5, 2, 8, 9});
        AlumnoB alumno2 = new AlumnoB("Alumno 2", new int[]{5, 3, 6, 7, 10, 6});
        AlumnoB alumno3 = new AlumnoB("Alumno 3", new int[]{7, 4, 9, 9, 9, 8});
        AlumnoB alumno4 = new AlumnoB("Alumno 4", new int[]{9, 9, 9, 9, 9, 9});

        // Crear lista de alumnos
        ArrayList<AlumnoB> alumnos = new ArrayList<>();
        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        alumnos.add(alumno4);

        // Crear Scanner para capturar opciones del menú
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Calcular la nota media de cada alumno");
            System.out.println("2. Calcular la nota media de cada módulo");
            System.out.println("3. Calcular cuántos alumnos aprobaron todos los módulos cursados");
            System.out.println("4. Calcular cuántos alumnos no cursaron algún módulo");
            System.out.println("5. Módulo con la nota media más alta");
            System.out.println("6. Mejor alumno por cada módulo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    MEJ07.calcularMediaAlumnos(alumnos);
                    break;
                case 2:
                    MEJ07.calcularMediaModulos(alumnos, modulos);
                    break;
                case 3:
                    MEJ07.calcularAprobadosTodosModulos(alumnos);
                    break;
                case 4:
                    MEJ07.calcularAlumnosNoCursaron(alumnos);
                    break;
                case 5:
                    MEJ07.calcularModuloConMejorMedia(alumnos, modulos);
                    break;
                case 6:
                    MEJ07.calcularMejorAlumnoPorModulo(alumnos, modulos);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 0);

        teclado.close();
    }
}
