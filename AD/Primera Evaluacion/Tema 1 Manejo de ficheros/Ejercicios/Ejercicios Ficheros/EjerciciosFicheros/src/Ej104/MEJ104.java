package Ej104;

import java.io.*;

public class MEJ104 {
  //Método para crear el fichero y escribir en él
    public void escrictura(String nombreFichero, String fichero) {
      try (BufferedWriter escribir = new BufferedWriter(new FileWriter(nombreFichero))){
            escribir.write(fichero);
          System.out.println("Fichero creado y contenido escrito con éxito.");
      } catch (IOException e) {
          System.out.println("Ha ocurrido un error al crear/escribir en el fichero.");
          e.printStackTrace();
      }
  }

  //Método para leer el contenido del fichero
    public void leerFichero(String nombreFichero) {
        try (BufferedReader lectura = new BufferedReader(new FileReader(nombreFichero))){
            String linea;
            System.out.println("Contenido del fichero: ");

            while ((linea = lectura.readLine()) !=null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el fichero.");
            e.printStackTrace();
        }
    }

}
