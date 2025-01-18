package Ej112B;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MEJ112 {

    public void escrictura() throws IOException {
        // Fichero aleatorio
        File fichero = new File("./src/Ej112B/alumnos.dat");

        // Abrimos el fichero para lectura y escritura
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Declaración de los arrays que contienen los datos
        String apellidos[] = {"FERNANDEZ", "GOMEZ", "CHEN", "SERRANO", "CASILLAS", "ALONSO"};
        int edad[] = {17, 20, 18, 17, 19, 21};  // Ajustado el tamaño del array
        Double nota[] = {7.5, 4.2, 6.5, 8.0, 3.2, 9.2};  // Ajustado el tamaño del array

        // Buffer para almacenar apellido
        StringBuilder buffer = null;

        // Número de elementos que contiene el array
        int numero = apellidos.length;

        // Recorremos el array
        for (int i = 0; i < numero; i++) {
            // Uso i+1 para identificar alumno
            file.writeInt(i + 1);

            buffer = new StringBuilder(apellidos[i]);
            buffer.setLength(10);  // Forzar que el apellido tenga 10 caracteres

            // Inserción del apellido
            file.writeChars(buffer.toString());

            // Inserción de la edad
            file.writeInt(edad[i]);

            // Inserción de la nota
            file.writeDouble(nota[i]);
        }

        // Cierre del fichero
        file.close();
    }

    public void lectura() throws IOException {
        File fichero = new File("./src/Ej112B/alumnos.dat");

        // Declaración del fichero aleatorio para lectura
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, edad, pos;
        Double nota;
        char apellidos[] = new char[10], aux;

        // Se posiciona al principio del fichero
        pos = 0;

        // El for se utiliza para recorrer el fichero
        for (;;) {
            // Se posiciona en posición pos
            file.seek(pos);

            // Se obtiene el identificador del alumno
            id = file.readInt();

            // Leer el apellido
            for (int i = 0; i < apellidos.length; i++) {
                aux = file.readChar();
                apellidos[i] = aux;
            }

            // Se transforma el array a cadena
            String apellido = new String(apellidos).trim();

            // Obtiene la edad
            edad = file.readInt();

            // Obtiene la nota
            nota = file.readDouble();

            System.out.println("ID: " + id + ", Apellido: " + apellido + ", Edad: " + edad + ", Nota media del curso: " + nota);

            // Al sumar 36 nos posicionamos en el siguiente alumno
            pos = pos + 36;

            // Si se recorren todos los bytes, sale del for
            if (file.getFilePointer() == file.length()) {
                break;
            }
        }

        // Cierra el fichero (fuera del ciclo)
        file.close();
    }
}