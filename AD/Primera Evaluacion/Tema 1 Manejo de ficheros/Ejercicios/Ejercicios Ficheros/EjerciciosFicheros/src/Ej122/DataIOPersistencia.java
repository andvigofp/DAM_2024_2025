package Ej122;

import java.io.*;

public class DataIOPersistencia  implements Persistencia{

    @Override
    public void escribirPersona(Persona persona, String ruta) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta))) {
            out.writeLong(persona.getId());
            out.writeUTF(persona.getDni());
            out.writeInt(persona.getEdad());
            out.writeFloat(persona.getSalario());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona leerDatos(String ruta) {
        Persona persona = null;
        try (DataInputStream in = new DataInputStream(new FileInputStream(ruta))) {
            long id = in.readLong();
            String dni = in.readUTF();
            int edad = in.readInt();
            float salario = in.readFloat();
            persona = new Persona(id, dni, edad, salario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }
}
