package Ej307;

import java.util.Scanner;

public class AppStudents {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ManageStudents manageStudents = new ManageStudents();

        MEj307 metodos = new MEj307();

        final String menu = "1. MATRICULAR UN ESTUDIANTE."
                + "\n2. DAR DE BAJA A UN ESTUDIANTE."
                + "\n3. ACTUALIZAR DATOS DE UN ESTUDIANTE."
                + "\n4. VER DATOS DE UN ESTUDIANTE."
                + "\n5. VER DATOS DE TODOS LOS ESTUDIANTES."
                + "\n6. SALIR.";

        int option =-1;

        while (true) {
            System.out.println(menu);
            option = teclado.nextInt();

            switch (option) {
                case 1: // 1. MATRICULAR ESTUDIANTE.
                    metodos.matricularEstudiante(teclado, manageStudents);
                    break;
                case 2: // 2. DAR DE BAJA A UN ESTUDIANTE
                    metodos.darBajaEstudiante(teclado, manageStudents);
                    break;
                case 3: // 3. ACTUALIZAR DATOS DE UN ESTUDIANTE.
                    metodos.actualizarDatosEstudiante(teclado, manageStudents);
                    break;
                case 4: // 4. VER DATOS DE UN ESTUDIANTE.
                    metodos.verDatosEstudiante(teclado, manageStudents);
                    break;
                case 5: // 5. VER DATOS DE TODOS LOS ESTUDIANTES.
                    metodos.verDatosTodosEstudiantes(manageStudents);
                    break;
                case 6: // 6. Salir
                    manageStudents.closeConection();
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-5");


            }
        }
    }
}

