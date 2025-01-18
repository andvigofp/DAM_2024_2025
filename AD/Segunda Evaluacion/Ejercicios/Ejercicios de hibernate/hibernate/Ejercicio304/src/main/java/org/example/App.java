package org.example;

import org.example.entity.Autor;
import org.example.entity.Libro;
import org.example.entity.Telefono;
import org.example.repository.AutorRepositorio;
import org.example.repository.LibroRepositorio;
import org.example.repository.TelefonoRepositorio;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {

    static Scanner teclado;
    static AutorRepositorio autorRepositorio;
    static LibroRepositorio libroRepositorio;
    static TelefonoRepositorio telefonoRepositorio;

    public static void main(String[] args) {
        System.out.println("Ejercicio 304");

        teclado = new Scanner(System.in);

        Session session = HibernateUtil.get().openSession();

        autorRepositorio = new AutorRepositorio(session);
        libroRepositorio = new LibroRepositorio(session);
        telefonoRepositorio = new TelefonoRepositorio(session);

        mostrarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n1. Insertar nueva fila" +
                    "\n2. Borrar fila" +
                    "\n3. Consultar" +
                    "\n4. Salir");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1: {
                    mostrarMenuInsertar();
                    break;
                }
                case 2: {
                    mostrarMenuBorrar();
                    break;
                }
                case 3: {
                    mostrarMenuConsultas();
                    break;
                }
                case 4: {
                    System.exit(0);
                    break;
                }
            }
        } while (opcion != 4);
    }


    //Mostrar menuInsertar
    public static void mostrarMenuInsertar() {
        int opcion = -1;
        do {
            System.out.println("\n1. Insertar nuevo autor" +
                    "\n2. Insertar nuevo libro" +
                    "\n3. Enlazar autor-libro" +
                    "\n4. Insertar teléfono para un autor" +
                    "\n4. Atrás");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    insertarAutor(false);
                    break;
                }
                case 2: {
                    insertarLibro(false);
                    break;
                }
                case 3: {
                    enlazarAutorLibro();
                    break;
                }
                case 4: {
                    insertarTelefonoAutor();
                    break;
                }
                case 5: {
                    break;
                }
            }
        } while (opcion < 1 || opcion > 5);
    }


    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.next(); // Lee el título del libro
    }

    public static String pedirStringS(String mensaje) {
        System.out.println(mensaje);
        if (teclado.hasNextLine()) {
            teclado.nextLine(); // Limpiar el buffer de entrada
        }
        return teclado.nextLine(); // Leer la línea completa, incluidos los espacios
    }


    public int pedirInt(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextInt();
    }

    // Método para pedir un precio y manejar posibles errores
    public static double pedirPrecio(String mensaje) {
        System.out.println(mensaje);
        while (!teclado.hasNextDouble()) {
            System.out.println("Por favor, introduzca un precio válido.");
            teclado.next();
        }
        double precio = teclado.nextDouble();
        teclado.nextLine(); // Limpiar el salto de línea residual
        return precio;
    }


    public static void insertarAutor(boolean vieneDeAutor) {
        String dni = pedirString("Introduzca el DNI del autor: ");
        String nombre = pedirString("Introduzca el nombre del autor: ");
        String localidad = pedirString("Introduzca el localidad del autor: ");

        Autor autor = new Autor(dni, nombre, localidad);
        autorRepositorio.insertarUno(autor);
    }


    public static void enlazarAutorLibro() {
        String dni = pedirString("Introduzca el DNI del autor");
        String titulo = pedirStringS("Título del libro");

        try (Session session = HibernateUtil.get().openSession()) {
            Transaction trx = session.beginTransaction();

            try {
                Autor autor = session.createQuery("FROM Autor WHERE dniAutor = :dni", Autor.class)
                        .setParameter("dni", dni)
                        .uniqueResult();

                Libro libro = session.createQuery("FROM Libro WHERE titulo = :titulo", Libro.class)
                        .setParameter("titulo", titulo)
                        .uniqueResult();

                if (autor != null && libro != null) {
                    Hibernate.initialize(autor.getListaLibros()); // Inicializar la colección `lazy`

                    autor.addListaLibros(libro);
                    session.saveOrUpdate(autor);
                    session.saveOrUpdate(libro);
                } else {
                    System.out.println("No se encontró el autor o el libro.");
                }

                trx.commit();
            } catch (Exception e) {
                if (trx != null) trx.rollback();
                e.printStackTrace();
            }
        }
    }


    public static void insertarLibro(boolean vieneDeAutor) {
        String titulo = pedirStringS("Introduzca el titulo del libro: ");
        double precio = pedirPrecio("Introduzca el precio del libro");

        Libro libro = new Libro(titulo, precio);
        libroRepositorio.insertarUno(libro);
    }


    public static void insertarTelefonoAutor() {
        String dni = pedirString("Introduzca el DNI del autor");
        String numTel = pedirString("Introduzca el número de telefono");

        try (Session session = HibernateUtil.get().openSession()) {
            Transaction trx = session.beginTransaction();

            try {
                Autor autor = session.createQuery("FROM Autor WHERE dniAutor = :dni", Autor.class)
                        .setParameter("dni", dni)
                        .uniqueResult();

                if (autor != null) {
                    // Crear el teléfono y relacionarlo con el autor
                    Telefono telefono = new Telefono(autor, numTel);
                    autor.setTelefono(telefono);

                    Hibernate.initialize(autor.getListaLibros()); // Inicializar la colección `lazy`

                    session.saveOrUpdate(autor);
                    session.saveOrUpdate(telefono);
                } else {
                    System.out.println("No se encontró un autor con el DNI: " + dni);
                }

                trx.commit();
            } catch (Exception e) {
                if (trx != null) trx.rollback();
                e.printStackTrace();
            }
        }
    }

    //Menú Mostar menuBorrar
    public static void mostrarMenuBorrar() {
        int opcion = -1;
        do {
            System.out.println("\n1. Borrar autor" +
                    "\n2. Borar libro" +
                    "\n3. Atrás");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1: {
                    String dni = pedirString("Introduzca el DNI del autor");
                    Autor autor = autorRepositorio.encontrarUnoPorString(dni);
                    if (autor != null) {
                        autorRepositorio.borrar(autor);
                    } else {
                        System.out.println("No se encontró un autor con el DNI: " + dni);
                    }
                    break;
                }
                case 2: {
                    String titulo = pedirStringS("Introduzca el título del libro");
                    Libro libro = libroRepositorio.encontrarUnoPorString(titulo);
                    if (libro != null) {
                        libroRepositorio.borrar(libro);
                        System.out.println("Libro borrado correctamente.");
                    } else {
                        System.out.println("No se encontró un libro con el título: " + titulo);
                    }
                    break;
                }
                case 3: {
                    break;
                }
            }
        } while (opcion != 3);
    }



    public static void mostrarMenuConsultas() {
        System.out.println("\n1. Visualizar datos de un libro a partir del título" +
                "\n2. Visualizar libros de un determinado autor" +
                "\n3. Visualizar todos los libros" +
                "\n4. Visualizar todos los autores y sus libros\n5. Atrás");
        int opcion = -1;
        do {
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    String titulo = pedirStringS("Introduzca el título de un libro");
                    Libro libro = libroRepositorio.encontrarUnoPorString(titulo);
                    if (libro != null) {
                        System.out.println(libro.toString());
                    } else {
                        System.out.println("No se encontró un libro con el título: " + titulo);
                    }
                    break;
                }
                case 2: {
                    String DNI = pedirString("Introduzca el DNI del autor");
                    Autor autor = autorRepositorio.encontrarUnoPorString(DNI);
                    if (autor != null) {
                        for (Libro libro : autor.getListaLibros())
                            System.out.println(libro.toString());
                    } else {
                        System.out.println("No se encontró un autor con el DNI: " + DNI);
                    }
                    break;
                }
                case 3: {
                    List<Libro> listaLibros = libroRepositorio.encontrarTodos();
                    for (Libro libro : listaLibros)
                        System.out.println(libro.toString());
                    break;
                }
                case 4: {
                    List<Autor> listaAutores = autorRepositorio.encontrarTodos();
                    for (Autor autor : listaAutores) {
                        System.out.println(autor.toString() + "\nLibros:");
                        for (Libro libro : autor.getListaLibros()) {
                            System.out.println(libro.toString());
                        }
                    }
                    break;
                }
                case 5: {
                    mostrarMenu();
                    break;
                }
            }
        } while (opcion < 1 || opcion > 5);
    }
}
