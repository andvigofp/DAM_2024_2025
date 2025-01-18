package Ej119;

import java.io.IOException;
import java.util.Scanner;

public class Ej119 {
    public static void main(String[] args) {
        EjemploFile ejemploFile = new EjemploFile();

        String rutaFichero = "./src/Ej119/archivoPrueba.txt";
        String copiaFichero = "./src/Ej119/archivoPruebaCopia.txt";

            try {
                //Crear un archivo nuevo
                ejemploFile.crearFichero(rutaFichero);

                //Mostrar la información del archivo
                ejemploFile.copiar(rutaFichero, copiaFichero);

                //Mostrar información de la copia
                ejemploFile.getFileInfo(copiaFichero);

                //Borrar la copia del archivo
                ejemploFile.borrarFichero(copiaFichero);
            }catch (IOException e) {
                System.out.println("Error. el archivo no existe");
                e.printStackTrace();
            }
        }
        }
