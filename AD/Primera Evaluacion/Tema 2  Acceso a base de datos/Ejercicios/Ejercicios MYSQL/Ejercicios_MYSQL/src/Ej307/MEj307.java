package Ej307;

import java.util.ArrayList;
import java.util.Scanner;

public class MEj307 {
    // 1. MATRICULAR ESTUDIANTE.
    public void matricularEstudiante(Scanner teclado, ManageStudents manageStudents) {
        System.out.println("Introduzca el DNI del alumno: ");
        String dni = teclado.next();

        System.out.println("Introduzca el nombre del alumno: ");
        String nombre = teclado.next();

        System.out.println("Introduzca el apellido del alumno: ");
        String apellido = teclado.next();

        System.out.println("Introduzca la edad del alumno: ");
        int edad = teclado.nextInt();

        Student st = new Student(dni, nombre, apellido, edad);

        manageStudents.addStudent(st);

        System.out.println("Alumno añadido correctamnete");
    }

    // DAR DE BAJA A UN ESTUDIANTE
    public void darBajaEstudiante(Scanner teclado, ManageStudents manageStudents) {
        System.out.println("Introduzca el DNI del alumno a borrar: ");
        String dni = teclado.next();

        if (manageStudents.deleteSudent(dni)) {
            System.out.println("Se ha a dado de baja el alumno de forma correcta.");
        }else {
            System.out.println("No se ha dado de baja ningún alumno.");
        }
    }

    // ACTUALIZAR DATOS DE UN ESTUDIANTE
    public void actualizarDatosEstudiante(Scanner teclado, ManageStudents manageStudents) {
        System.out.println("Introduzca el DNI del alumno:");
        String dni = teclado.next();

        System.out.println("Introduzca el nombre del alumno: ");
        String nombre = teclado.next();

        System.out.println("Introduzca el apellido del alumno: ");
        String apellido = teclado.next();

        System.out.println("Introduzca la edad del alumno: ");
        int edad = teclado.nextInt();

        Student st = new Student(dni, nombre, apellido, edad);

        if (manageStudents.modifyStudent(st)) {
            System.out.println("Se ha actualizado los datos de forma correcta.");
        }else {
            System.out.println("No se han podido modificar los datos.");
        }
    }

    // VER DATOS DE UN ESTUDIANTE
    public void verDatosEstudiante(Scanner teclado, ManageStudents manageStudents) {
        Student st = new Student();

        System.out.println("Introduzca el DNI del alumno a consultar: ");
        String dni = teclado.next();

        st = manageStudents.getStudent(dni);

        if (st !=null) {
            System.out.println("Mostrando datos del alumno: " + dni + "\nNombre: " + st.getName() +
                    "\nApellido " + st.getSurname() + "\nEdad " + st.getAge());
        }else {
            System.out.println("El estudiante con DNI " + dni + " no existe.");
        }

    }

    // VER DATOS DE TODOS LOS ESTUDIANTES
    public void verDatosTodosEstudiantes(ManageStudents manageStudents) {
        ArrayList<Student> listaEstudiantes = manageStudents.getSudentsList();
        System.out.println("Mostrando información de los estudiantes: ");

        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes matriculados.");
        }else {
            for (Student student: listaEstudiantes) {
                System.out.println("\nMostrando datos del alumno: " + student.getId() +
                        "\nNombre: " + student.getName() + "\nApellido: " + student.getSurname() +
                        "\nEdad: " + student.getAge());
            }
        }
    }
}

