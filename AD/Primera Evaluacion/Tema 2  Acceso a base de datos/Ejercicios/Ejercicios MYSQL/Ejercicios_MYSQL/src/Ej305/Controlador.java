package Ej305;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controlador implements ActionListener{
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SUMAR")) {
            String[] param = vista.getCampos();
            modelo.sumar(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
            int resultado = modelo.getSuma();
            vista.escribirResultadoSuma(Integer.toString(resultado));
        }
    }
}
