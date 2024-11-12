package Ej109;

import java.io.*;

/**
 * Crea una clase FicheroBinario que represente a un fichero binario. La clase tendrá un atributo de tipo File, que almacenará el fichero correspondiente. Además, tendrá los siguientes métodos:
 *
 * Constructor.
 * Getter.
 * public void escribir(String texto): escribe en el propio fichero el texto pasado por parámetro.
 * public void leer(): muestra por consola el contenido del fichero.
 * public void copiar(FicheroBinario ficheroDestino): copia el contenido del fichero en el fichero de destino.
 * NOTA: Debes usar las clases FileInputStream y FileOutputStream.
 *
 * A continuación, se pide crear una clase ManejoFicherosBinarios, que implemente el método main. En esta clase debes crear dos ficheros binarios origen.bin y destino.bin y hacer lo siguiente:
 *
 * Escribir en el fichero de origen el texto: ESTE ES EL TEXTO DE ORIGEN.
 * Leer y mostrar el contenido del fichero por consola.
 * Copia el contenido al fichero de destino.
 */
public class FicheroBinario {
    private File file;

    public FicheroBinario() {
    }

    public FicheroBinario(File file) {
        this.file = file;
    }


    public File getFile() {
        return file;
    }

    public void escrictura(String texto) throws IOException {

        //Creamos los steam de escrictura
        FileOutputStream escrictura = new FileOutputStream(this.getFile());
        DataOutputStream datos = new DataOutputStream(escrictura);

        datos.writeUTF(texto);

        //Ceramos el steam
        datos.close();
    }

    public void lectura() throws IOException {

        FileInputStream leer = new FileInputStream(this.getFile());
        DataInputStream datos = new DataInputStream(leer);


        try {
            while (true) {
                //Leemos el nombre del departamento
                System.out.println(datos.readUTF());
            }
        }catch (EOFException eo) {
            //Cerrar Stream
            datos.close();
        }
    }

    public void copiar(FicheroBinario ficheroDestino) {
        try {
            //Creación del stream de lectura
            InputStream lectura = new FileInputStream(this.getFile());

            //Creación del stream de escritura
            OutputStream escrictura = new FileOutputStream(ficheroDestino.getFile());

            //Creación del buffer
            byte[] buffer = new byte[1024];
            int leidos;

            //Recorremos los elementos leidos del buffer
            while ((leidos = lectura.read(buffer)) > 0) {
                //Escribimos en el fichero destino
                escrictura.write(buffer, 0, leidos);
            }

            //Cerramos los streams
            lectura.close();
            escrictura.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
