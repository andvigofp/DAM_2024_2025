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
            System.out.println("La edad no es un número válido: " + datos[3]);
            return;
        }

        if (e.getActionCommand().equals("GUARDAR")) {
            Student student = modelo.getStudent(datos[0]);
            Student new_student = new Student(datos[0], datos[1], datos[2], age);
            if (student == null) {
                modelo.addStudent(new_student);
            } else {
                modelo.modifyStudent(new_student);
            }
        } else if (e.getActionCommand().equals("ACTUALIZAR")) {
            Student new_student = new Student(datos[0], datos[1], datos[2], age);
            modelo.modifyStudent(new_student);

        } else if (e.getActionCommand().equals("BORRAR")) {
            if (modelo.getStudent(datos[0]) != null) {
                modelo.deleteStudent(datos[0]);
            }
        }

        actualizarTabla(); // Reutiliza el método
    }
}
