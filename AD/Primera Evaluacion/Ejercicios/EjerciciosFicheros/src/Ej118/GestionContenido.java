package Ej118;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {

    public GestionContenido() {
        super();
    }

    /**
     * Significa sobre escribir, esto ayuda entre otras cosas
     *  a reducir el código, cuando se crea una clase que hereda
     *  de otra clase, hereda todos sus métodos, propiedades y atributos
     *
     * Cuando se hace override significa que sobrescribes
     * un método de la clase padre, ya sea para mejorar
     *  el método o para que se acople a su clase hija.
     **/

    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }

    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }

    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes atrbs){
        System.out.println("\t"+nombre);
    }

    @Override
    public void characters(char[] ch, int inicio, int longitud){
        String car=new String(ch, inicio, longitud);
        //quitar saltos de linea
        car = car.replaceAll("[\t\n]", "");
        System.out.println("\t\t"+ car);
    }
}
