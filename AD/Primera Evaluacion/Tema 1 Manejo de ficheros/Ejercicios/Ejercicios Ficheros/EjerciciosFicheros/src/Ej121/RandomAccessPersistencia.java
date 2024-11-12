package Ej121;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessPersistencia implements Persistencia {

    @Override
    public void escribirPersona(Persona persona, String ruta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.writeLong(persona.getId());
            raf.writeUTF(persona.getDni());
            raf.writeInt(persona.getEdad());
            raf.writeFloat(persona.getSalario());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona leerDatos(String ruta) {
        Persona persona = null;
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long id = raf.readLong();
            String dni = raf.readUTF();
            int edad = raf.readInt();
            float salario = raf.readFloat();
            persona = new Persona(id, dni, edad, salario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }
}