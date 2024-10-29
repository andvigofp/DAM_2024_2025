package Ej308;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actualizarTabla() {
        ArrayList<Student> listaEstudiantes = modelo.getStudentsList();
        vista.actualizarDatos(listaEstudiantes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] datos = vista.getDatos();
        int age;
        try {
            age = Integer.parseInt(datos[3]);
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Error: La edad ingresada no es un número válido.");
            return;
        }

        if (e.getActionCommand().equals("GUARDAR")) {
            Student newStudent = new Student(datos[0], datos[1], datos[2], age);
            if (modelo.addStudent(newStudent)) {
                vista.mostrarMensaje("Estudiante guardado exitosamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo guardar el estudiante, el ID ya existe o hubo otro problema.");
            }

        } else if (e.getActionCommand().equals("ACTUALIZAR")) {
            Student updatedStudent = new Student(datos[0], datos[1], datos[2], age);
            if (modelo.modifyStudent(updatedStudent)) {
                vista.mostrarMensaje("Estudiante actualizado exitosamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo actualizar el estudiante, revisa los datos ingresados.");
            }

        } else if (e.getActionCommand().equals("BORRAR")) {
            if (modelo.deleteStudent(datos[0])) {
                vista.mostrarMensaje("Estudiante borrado exitosamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo borrar el estudiante, verifica que el ID exista.");
            }
        }

        vista.actualizarDatos(modelo.getStudentsList()); // Actualiza la tabla después de cada operación
    }
}