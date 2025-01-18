package Ej108;

import java.io.*;
import java.text.Format;
import java.util.LinkedList;

public class MEJ108 {
    public void escrictura() throws IOException {
        File file = new File("./src/Ej108/Empleados.dat");

        //Creamos los steam de escrictura
        FileOutputStream escrictura = new FileOutputStream(file);
        DataOutputStream datos = new DataOutputStream(escrictura);

        //Inicializacion de los parámetros a escribir
        String departamento[] = {"Contabilidad", "Informática", "Dirección", "Análisis", "Finanzas", "Hardware"};
        int numeroEmpleados[] = {3,10,2,5,4,8};

        //Escribimos la información
        for (int i=0; i<numeroEmpleados.length; i++) {
            datos.writeUTF(departamento[i]); //Insertar nombre departamento
            datos.writeInt(numeroEmpleados[i]); //Insertar número de empleados

        }

        //Ceramos el steam
        datos.close();
    }

    public void lectura() throws IOException {
        File file = new File("./src/Ej108/Empleados.dat");

        FileInputStream leer = new FileInputStream(file);
        DataInputStream datos = new DataInputStream(leer);

        String nombreDepartamento;
        int numeroEmpelados;
        try {
            while (true) {
                //Leemos el nombre del departamento
                nombreDepartamento = datos.readUTF();

                //Leemos el número de empleados del departamento
                numeroEmpelados = datos.readInt();
                System.out.println("Nombre depertamento " + nombreDepartamento + ", Número empleados " + numeroEmpelados);
            }
        }catch (EOFException eo) {
            //Cerrar Stream
            datos.close();
        }
    }
}
