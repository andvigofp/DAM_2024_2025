package EJ107B;

public class AlumnoB {
        private String nombre;
        private int[] notas; // Las notas para cada módulo

        public AlumnoB(String nombre, int[] notas) {
            this.nombre = nombre;
            this.notas = notas;
        }

        public String getNombre() {
            return nombre;
        }

        public int[] getNotas() {
            return notas;
        }
    }

