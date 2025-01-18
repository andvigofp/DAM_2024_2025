package Ej107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Nombre_Alumno# LMSXI # SI # BD # PRO # CD # FOL;Alumno 1  # 4     #    # 5  #  2  # 8  # 9;Alumno 2     # 5     # 3  # 6  #  7  # 10 # 6;Alumno 3     # 7     # 4  # 9  #  9  # 9  # 8;
 *
 * Permita obtener:
 *
 * Calcular la nota media de cada alumno en el ciclo, teniendo en cuenta solo aquellos módulos que han cursado.
 *
 * Calcular la nota media de cada módulo, teniendo en cuenta solo aquellos alumnos que cursaron el módulo.
 *
 * Calcular el número de alumnos que aprobaron todos los módulos que han cursado.
 *
 * Calcular el número de alumnos que no han cursado algún módulo.
 *
 * Indicar cuál es el módulo que tiene la nota media más alta.
 *
 * Indicar cuál es el alumno que mejor nota ha sacado por módulo.
 */

public class EJ107 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String[] materias = {"LMSXI", "SI", "BD", "PRO", "CD", "FOL"};
        CicloOperaciones operaciones = new CicloOperaciones();

        boolean salir = false;
        while (!salir) {
            operaciones.Menu();
            System.out.println("Seleccione una opción:");

            int opcion;
            while (true) {
                try {
                    opcion = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    operaciones.agregarAlumno(sc, alumnos, materias);
                    break;
                case 2:
                    operaciones.notaMediaAlumno(alumnos);
                    break;
                case 3:
                    operaciones.notaMediaModulo(alumnos, materias);
                    break;
                case 4:
                    operaciones.alumnosAprobaronTodo(alumnos);
                    break;
                case 5:
                    operaciones.alumnosNoCursaronTodo(alumnos, materias.length);
                    break;
                case 6:
                    operaciones.notaMediaMasAlta(alumnos, materias);
                    break;
                case 7:
                    operaciones.alumnoMejorNotaPorModulo(alumnos, materias);
                    break;
                case 8:
                    salir = true;
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 8.");
            }
        }

        sc.close();
    }
}