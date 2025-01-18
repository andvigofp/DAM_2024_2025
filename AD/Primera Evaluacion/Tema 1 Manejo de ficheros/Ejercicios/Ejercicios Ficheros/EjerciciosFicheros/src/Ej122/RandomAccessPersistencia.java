package Ej122;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RandomAccessPersistencia implements Persistencia {

    private static final int DNI_LENGTH = 9; //El DNI es de 9 caracteres
    private static final int PERSONA_SIZE = Long.BYTES + (DNI_LENGTH * 2) + Integer.BYTES + Float.BYTES; //Tamaño fijo de una Persona

    @Override
    public void escribirPersona(Persona persona, String ruta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek(raf.length()); //Colocarse al final del fichero
            escribirPersonaEnArchivo(raf, persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona leerDatos(String ruta) {
        Persona persona = null;
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            persona = leerPersonaDesdeArchivo(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek(raf.length()); // Colocarse al final del fichero
            for (Persona persona : personas) {
                escribirPersonaEnArchivo(raf, persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Persona> leerTodo(String ruta) {
        ArrayList<Persona> personas = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                personas.add(leerPersonaDesdeArchivo(raf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public Persona leerPersona(int posicion, String ruta) {
        Persona persona = null;
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            raf.seek((posicion - 1) * PERSONA_SIZE); //Posicionarse en la persona indicada
            persona = leerPersonaDesdeArchivo(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    //Método para añadir un emppleado
    public void add(int posicion, String ruta, Persona persona) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek((posicion - 1) * PERSONA_SIZE); //Posicionarse en la persona indicada
            escribirPersonaEnArchivo(raf, persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para sumar el salario de un empleado
    public float sumarSalario(int posicion, String ruta, float incremento) {
        float nuevoSalario = 0.0f;
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek((posicion - 1) * PERSONA_SIZE + Long.BYTES + (DNI_LENGTH * 2) + Integer.BYTES); //Posicionarse en el salario
            float salarioActual = raf.readFloat();
            nuevoSalario = salarioActual + incremento;
            raf.seek((posicion - 1) * PERSONA_SIZE + Long.BYTES + (DNI_LENGTH * 2) + Integer.BYTES); //Volver a la posición del salario
            raf.writeFloat(nuevoSalario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nuevoSalario;
    }

    //Métodos auxiliares para escribir y leer una Persona
    private void escribirPersonaEnArchivo(RandomAccessFile raf, Persona persona) throws IOException {
        raf.writeLong(persona.getId());
        String dni = String.format("%-9s", persona.getDni()); // Fijar tamaño de 9 caracteres para el DNI
        raf.writeChars(dni);
        raf.writeInt(persona.getEdad());
        raf.writeFloat(persona.getSalario());
    }

    private Persona leerPersonaDesdeArchivo(RandomAccessFile raf) throws IOException {
        long id = raf.readLong();

        char[] dniChars = new char[DNI_LENGTH];
        for (int i = 0; i < DNI_LENGTH; i++) {
            dniChars[i] = raf.readChar();
        }
        String dni = new String(dniChars).trim();

        int edad = raf.readInt();
        float salario = raf.readFloat();
        return new Persona(id, dni, edad, salario);
    }
}