package org.example;

import org.example.entidades.Actor;
import org.example.repositorio.ActorRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Session session;
    static ActorRepositorio actorRepositorio;
   static Scanner teclado;
    public static void main( String[] args )
    {
        System.out.println("Test");

        session = HibernateUtil.get().openSession();

        actorRepositorio = new ActorRepositorio(session);




        int opcion = -1;

        while (opcion !=15) {
            final String menu = "1. Crear actor\n" +
                    "2. Eliminar actor\n" +
                    "3. Crear pelicula\n" +
                    "4. Eliminar pelicula\n" +
                    "5. Crear premio\n" +
                    "6. Eliminar premio\n" +
                    "7. Modificar género de una película\n" +
                    "8. Asignar un premio a una película\n" +
                    "9. Asignar un actor a una película\n" +
                    "10. Asignar una película a una sala en una fecha y una hora\n" +
                    "11. Consulta 1\n" +
                    "12. Consulta 2\n" +
                    "13. Consulta 3\n" +
                    "14. Consulta 4\n" +
                    "15. Salir";

            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:


            }
        }

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }


    public static String pedirString(String mensaje){
        System.out.println(mensaje);
        return teclado.next();
    }


    public static int pedirInt(String mensaje){
        while(true){
            try{
                System.out.println(mensaje);
                return teclado.nextInt();
            }catch (Exception e){}
        }
    }

    public void crearActor() {
        String nombre = pedirString("Introduce el nombre de actor");
        
    }
}
