package Ej112;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Alumno implements Serializable {
    private String apellido;
    private int edad;
    private double nota;

    public Alumno() {
    }

    public Alumno(String apellido, int edad, double nota) {
        this.apellido = apellido;
        this.edad = edad;
        this.nota = nota;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Apellido: " + apellido  + ", Edad: " + edad + ", Nota: " + nota;
    }

    // Método para escribir en binario de forma aleatoria sin repetir datos
    public void escribirBinarioAleatorio(String nombreFichero) {
        String[] apellidos = {"FERNANDEZ", "LOPEZ", "GOMEZ", "SERRANO", "CASILLAS", "ALONSO"};
        int[] edades = {17, 20, 18, 17, 19, 21, 20};
        double[] notas = {7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9};

        // Lista para almacenar los alumnos generados sin duplicados
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        HashSet<String> apellidosUnicos = new HashSet<>();

        // Generar y agregar alumnos
        for (int i = 0; i < apellidos.length; i++) {
            String apellido = apellidos[i];
            if (!apellidosUnicos.contains(apellido)) {
                apellidosUnicos.add(apellido); // Verificamos que el apellido no esté duplicado
                Alumno alumno = new Alumno(apellido, edades[i], notas[i]);
                listaAlumnos.add(alumno);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "rw")) {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                // Mover el puntero de lectura a la posición correspondiente
                long pos = i * 120;
                raf.seek(pos);

                // Escribir los datos en formato binario
                Alumno alumno = listaAlumnos.get(i);
                raf.writeUTF(String.format("%-20s", alumno.getApellido())); // 20 caracteres para el apellido
                raf.writeInt(alumno.getEdad()); // Escribir la edad
                raf.writeDouble(alumno.getNota()); // Escribir la nota
            }
            System.out.println("Información escrita en el fichero binario sin duplicados.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
            e.printStackTrace();
        }
    }

    // Método para leer el contenido de un fichero binario de forma aleatoria
    public void leerFicheroBinarioAleatorio(String nombreFichero) {
        try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "r")) {
            long numeroRegistros = raf.length() / 120; // Asumiendo que cada registro ocupa 120 bytes

            // HashSet para almacenar los índices ya leídos
            HashSet<Integer> registrosLeidos = new HashSet<>();
            Random random = new Random();

            // Leer registros sin repetir
            for (int i = 0; i < numeroRegistros; i++) {
                int registroAleatorio;
                do {
                    registroAleatorio = random.nextInt((int) numeroRegistros);
                } while (registrosLeidos.contains(registroAleatorio)); // Repetir si ya fue leído

                // Añadir a la lista de registros leídos
                registrosLeidos.add(registroAleatorio);

                // Mover el puntero de lectura a la posición correspondiente
                long pos = registroAleatorio * 120;
                raf.seek(pos);

                // Leer los datos de forma aleatoria
                String apellido = raf.readUTF().trim();
                int edad = raf.readInt();
                double nota = raf.readDouble();

                System.out.println("Alumno Aleatorio: " + apellido + ", Edad: " + edad + ", Nota: " + nota);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
            e.printStackTrace();
        }
    }
}