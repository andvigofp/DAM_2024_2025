package Ej106;

import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    private ArrayList<Contacto> listaContactos; //Lista para almacenar contactos

    //Constructor
    public Agenda() {
        listaContactos = new ArrayList<>();
    }

    //Método para agregar un contacto
    public void agregarContacto() {
        Scanner teclado = new Scanner(System.in);
        String nombre, email;
        int telefono;

        System.out.println("Introduce el nombre del contacto:");
        nombre = teclado.nextLine();

        //Verificar si el contacto ya existe
        if (contactoExiste(nombre)) {
            System.out.println("El contacto con nombre " + nombre + " ye existe:");
            return; //Salir del método si el contacto ya está en la lista
        }

        System.out.println("Introduce el telefóno de contacto: ");
        telefono = teclado.nextInt();

        System.out.println("Introduce el email del contacto:");
        email = teclado.nextLine();

        Contacto contacto = new Contacto(nombre, telefono, email);
        listaContactos.add(contacto);

        System.out.println("Contacto agregado con éxito.");
    }

    //Método para verificar si un contacto ya existe en la lista por nombre
    private boolean contactoExiste(String nombre) {
        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return true; //Si el contacto existe, retorma true
            }
        }
        return false; //Si no encuentra el contacto, retorma false
    }

    //Método para mostrar todos los contactos
    public void mostrarConatctos() {
        if (listaContactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        }else {
            System.out.println("Lista de contactos:");

            for (int i=0; i<listaContactos.size(); i++) {
                System.out.println("Contacto " + (i+1) + ":");
                listaContactos.get(i).mostrarContacto();
                System.out.println("----------------------");
            }
        }
    }

    //Método para elimianar un contacto
    public void eliminarContacto() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el nombre del contacto a elminar: ");
        String nombreElminar = teclado.next().toLowerCase();

        boolean encontrado = false;
        for (int i=0; i<listaContactos.size(); i++) {
            if (listaContactos.get(i).getNombre().equalsIgnoreCase(nombreElminar)) {
                listaContactos.remove(i);
                encontrado = true;
                System.out.println("CDontacto eliminado con éxito.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Contacto no encontado.");
        }
    }
}
