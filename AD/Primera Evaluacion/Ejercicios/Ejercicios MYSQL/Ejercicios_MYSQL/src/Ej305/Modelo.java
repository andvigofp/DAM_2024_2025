package Ej305;

public class Modelo {
    private int resultadoSuma;

    public void sumar(int numero1, int numero2) {
        this.resultadoSuma = numero1 + numero2;
    }

    public int getSuma() {
        return this.resultadoSuma;
    }
}
