package EJ108;

public class Material {
    private String titulo;
    private String id;

    public Material(String titulo, String id) {
        this.titulo = titulo;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", ID: " + id;
    }
}
