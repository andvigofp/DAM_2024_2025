package EJ108;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private Map<String, Material> catalogo;
    private Map<String, Material> prestados;

    public Biblioteca() {
        catalogo = new HashMap<>();
        prestados = new HashMap<>();
    }

    public void agregarMaterial(Material material) {
        catalogo.put(material.getId(), material);
    }

    public void prestarMaterial(String id) {
        Material material = catalogo.get(id);
        if (material != null && !prestados.containsKey(id)) {
            prestados.put(id, material);
            catalogo.remove(id);
            System.out.println("Material prestado: " + material);
        } else if (prestados.containsKey(id)) {
            System.out.println("El material ya está prestado.");
        } else {
            System.out.println("Material no encontrado en el catálogo.");
        }
    }

    public void devolverMaterial(String id) {
        Material material = prestados.get(id);
        if (material != null) {
            prestados.remove(id);
            catalogo.put(id, material);
            System.out.println("Material devuelto: " + material);
        } else {
            System.out.println("El material no estaba prestado.");
        }
    }

    public void verPrestados() {
        if (prestados.isEmpty()) {
            System.out.println("No hay materiales prestados.");
        } else {
            System.out.println("Materiales prestados:");
            for (Material material : prestados.values()) {
                System.out.println(material);
            }
        }
    }

    public void verCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        } else {
            System.out.println("Catálogo de materiales:");
            for (Material material : catalogo.values()) {
                System.out.println(material);
            }
        }
    }
}
