package Ej108;

import java.io.IOException;

/**
 * Desarrolla un programa Java que permita:
 *
 * Escribir en un fichero binario Empleados.dat de manera secuencial, la siguiente información:
 *
 * Departamento "Contabilidad","Informática","Dirección","Análisis","Finanzas","Hardware"
 * Nª Empleados 3,10,2,5,4,8
 *
 * Mostrar la información del fichero anterior de forma secuencial.
 */
public class EJ108 {
    public static void main(String[] args) throws IOException {
        MEJ108 ficheroBinario = new MEJ108();

        ficheroBinario.escrictura();
        ficheroBinario.lectura();
    }
}
