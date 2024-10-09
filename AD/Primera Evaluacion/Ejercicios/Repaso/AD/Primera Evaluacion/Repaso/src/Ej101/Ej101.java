package Ej101;

/**
 * Realiza un programa en Java que usando una variable estática
 *
 * String texto = “4;5;7;8;4”;
 *
 * copiar rápido
 * Para copiar el texto anterior podéis pulsar en el botón que aparece en la parte derecha del cuadro anterior.
 *
 * Separe los elementos por el carácter ; y los sume.
 */
public class Ej101 {
    //Variable tipo String
   static String texto = "4;5;7;8;4";
    public static void main(String[] args) {
        int suma=0;
        String[] caracter= texto.split(";");
        for (String separado: caracter) {
            suma+= Integer.parseInt(separado);
        }
        System.out.println("El total de la suma es: " + suma);
    }
}
