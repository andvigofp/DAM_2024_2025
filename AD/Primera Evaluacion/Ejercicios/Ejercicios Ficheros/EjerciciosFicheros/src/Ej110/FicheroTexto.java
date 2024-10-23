package Ej110;

import java.io.*;

public class FicheroTexto {
    private File fichero;

    public FicheroTexto() {
    }

    public FicheroTexto(File fichero) {
        this.fichero = fichero;
    }


    public File getFichero() {
        return fichero;
    }

    public void escribir(String texto) throws IOException {
        FileWriter fichero = new FileWriter(this.getFichero());
        PrintWriter pw = new PrintWriter(fichero);

        //Escribimos en el fichero
        pw.println(texto);

        //Cerramos
        fichero.close();
    }

    public void leer() throws IOException {
        FileReader leer = new FileReader(this.getFichero());
        BufferedReader br = new BufferedReader(leer);

        //Lectura Fichero
        String linea;

        while ((linea = br.readLine())!=null) {
            System.out.println(linea);
        }
    }
}
