package EJ108;

public class Libro extends Material{
    private String autor;

    public Libro(String titulo, String id, String autor) {
        super(titulo, id);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Autor: " + autor;
    }
}