package Ej120;

import java.io.*;
import java.util.List;

public class Persintencia {
    //Método para escribir un objeto Piscina a un archivo
    public static void write(Piscina object, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(object);
        }
    }

    //Método para leer un objeto Piscina desde un archivo
    public static Piscina read(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (Piscina) ois.readObject();
        }
    }

    // Método para escribir una lista de objetos Piscina en un archivo
    public static void writeList(List<Piscina> piscinas, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(piscinas);
        }
    }

    // Método para leer una lista de objetos Piscina desde un archivo
    @SuppressWarnings("unchecked")
    public static List<Piscina> readList(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<Piscina>) ois.readObject();
        }
    }
}
