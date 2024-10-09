package EJ108;

public class Revista extends Material{
    private int numero;

    public Revista(String titulo, String id, int numero) {
        super(titulo, id);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return super.toString() + ", Número: " + numero;
    }
}
