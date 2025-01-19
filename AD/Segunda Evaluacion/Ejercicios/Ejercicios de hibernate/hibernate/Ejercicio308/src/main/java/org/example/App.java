package org.example;

import org.example.entity.*;
import org.example.repository.GeneroRepositorio;
import org.example.repository.PeliculaRepositorio;
import org.example.repository.PersonaRepositorio;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {
    static Scanner teclado;

    public static void main(String[] args) {
        System.out.println("Ejercicio 308");

        Session session = HibernateUtil.get().openSession();

        GeneroRepositorio generoRepositorio = new GeneroRepositorio(session);
        PeliculaRepositorio peliculaRepositorio = new PeliculaRepositorio(session);
        PersonaRepositorio personaRepositorio = new PersonaRepositorio(session);

        teclado = new Scanner(System.in);

        int opcion = -1;

        do {
            opcion = pedirInt("1. Agregar nuevos géneros\n" +
                    "2. Eliminar registros de un cierto género\n" +
                    "3. Buscando género por identificador\n" +
                    "4. Buscando género  por nombre\n" +
                    "5. Devolver todos los registros de la tabla género\n" +
                    "6. Guardar nueva persona\n" +
                    "7. Buscar persona por id\n" +
                    "8. Buscar persona según fecha de nacimiento\n" +
                    "9. Buscar persona por veces que ganó el premio mejor actriz.\n" +
                    "10. Buscar persona por veces que ganó el oscar.\n" +
                    "11. Agregar nueva pelicula \n" +
                    "12. Eliminar pelicula.\n" +
                    "13. Buscar película por título\n" +
                    "14. Buscando película por identificador\n" +
                    "15. Devolver todos las películas\n" +
                    "16. Recuperar todas las películas en las que participó cierta persona.\n"
                    + "17. Salir");



            switch (opcion) {
                case 1: {
                    String nombreGenero = pedirString("Nombre del nuevo genero");
                    generoRepositorio.guardar(new Genero(nombreGenero));
                    break;
                }
                case 2: {
                    int idGenero = pedirInt("Id del genero a eliminar");
                    try {
                        Genero genero = generoRepositorio.buscarPorId(idGenero);
                        generoRepositorio.eliminar(genero);
                    }catch (Exception e) {
                        System.out.println("Error al eliminar un género");
                    }
                    break;
                }
                case 3: {
                    int idGenero = pedirInt("Id del genero a buscar");
                    Genero genero = generoRepositorio.buscarPorId(idGenero);
                    System.out.println(genero.toString());
                    break;
                }
                case 4: {
                    String cadena = pedirString("Nombre del genero a buscar");
                    List<Genero> listaGenero = generoRepositorio.buscarPorCadena(cadena);
                    for (Genero genero: listaGenero)
                        System.out.println(genero.toString());
                    break;
                }
                case 5: {
                    ArrayList<Genero> listaGeneros = (ArrayList<Genero>) generoRepositorio.buscarTodo();
                    for(Genero genero: listaGeneros)
                        System.out.println(genero.toString());
                    break;
                }
                case 6: {
                    String actizActor = "";
                    do{
                        actizActor = pedirString("Es actriz o actor?").trim();
                    }while(!actizActor.equals("actor") && !actizActor.equals("actriz") );

                    int anhoNacimiento = pedirInt("Año de nacimiento");
                    if (actizActor.equals("actor")) {
                        String nombre = pedirString("Nombre del actor");
                        String dni = pedirString("DNI del actor");
                        int numeroOscars = pedirInt("Número de oscars");
                        Persona actor = new Actor(nombre, dni, numeroOscars, anhoNacimiento);
                        personaRepositorio.guardar(actor);
                    }else if(actizActor.equals("actriz")) {
                        String nombre = pedirString("Nombre de la actriz");
                        String dni = pedirString("DNI de la actriz");
                        int numeroMejorActriz = pedirInt("Número de veces ganó el premio mejor actriz");
                        Persona actriz = new Actriz(nombre, dni, numeroMejorActriz, anhoNacimiento);
                        personaRepositorio.guardar(actriz);
                    }
                    break;
                }
                case 7: {
                    int id = pedirInt("Introduzca el ID ");
                    Persona persona = personaRepositorio.buscarPorId(id);
                    System.out.println(persona.toString());
                    break;
                }
                case 8: {
                    int anho = pedirInt("Introduzca el año a partir del cual quieres buscar");
                    List<Persona> listaPersonas = personaRepositorio.buscarPorAnho(anho);
                    for (Persona persona: listaPersonas)
                        System.out.println(persona.toString());
                    break;
                }
                case 9: {
                    int vecesGano = pedirInt("Introduzca el número de veces que alguien ganó el premio a la mejor actriz.");
                    ArrayList<Persona> listaPersonas = (ArrayList<Persona>) personaRepositorio.buscarPorMejorActriz(vecesGano);
                    for(Persona persona: listaPersonas)
                        System.out.println(persona.toString());
                    break;
                }
                case 10: {
                    int vecesGano = pedirInt("Introduzca el número de veces que alguien ganó el premio a la mejor actriz.");
                    ArrayList<Persona> listaPersonas = (ArrayList<Persona>) personaRepositorio.buscarPorMejorActor(vecesGano);
                    for(Persona persona: listaPersonas)
                        System.out.println(persona.toString());
                    break;
                }
                case 11: {
                    String titulo = pedirString("Introduzca el nombre de la película");
                    int anho = pedirInt("Introduzca el año de la película");
                    int idGenero = pedirInt("Introduzca el ID del género de la película");
                    try {
                        Genero genero = generoRepositorio.buscarPorId(idGenero);
                        Pelicula pelicula = new Pelicula(titulo, anho, genero);
                        peliculaRepositorio.guardar(pelicula);
                    } catch (Exception e) {
                        System.out.println("ID del género no es válido");
                    }
                    break;
                }
                case 12: {
                    int idPelicula = pedirInt("Introduzca el ID de la película a eliminar");
                    try {
                        Pelicula pelicula = peliculaRepositorio.buscarPorId(idPelicula);
                        peliculaRepositorio.eliminar(pelicula);
                    }catch (Exception e) {
                        System.out.println("No exista ese ID para la película");
                    }
                    break;
                }
                case 13: {
                    String titulo = pedirString("Introduzca el titulo de la película");
                    try {
                        List<Pelicula> listaPeliculas = peliculaRepositorio.buscarPorCadena(titulo);
                        for(Pelicula pelicula: listaPeliculas)
                            System.out.println(pelicula.toString());
                    }catch (Exception e) {
                        System.out.println("No existe una película con ese título");
                    }
                    break;
                }
                case 14: {
                    int id = pedirInt("Introduzca el id de la película");
                    try {
                        Pelicula pelicula = (Pelicula) peliculaRepositorio.buscarPorId(id);
                        System.out.println(pelicula.toString());
                    }catch (Exception e) {
                        System.out.println("No existe una película con ese ID");
                    }
                    break;
                }
                case 15: {
                    ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) peliculaRepositorio.buscarTodo();
                    for (Pelicula pelicula: listaPeliculas)
                        System.out.println(pelicula.toString());
                    break;
                }
                case 17: {
                    System.exit(0);
                }
            }

        }while(opcion != 17);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    public static int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }

    public static Double pedirDouble(String string) {
        System.out.println(string);
        return teclado.nextDouble();
    }

    public static String pedirString(String string) {
        System.out.println(string);
        return teclado.next();
    }

}
