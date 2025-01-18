package Ej504;

public class Estudiante {
    private String matricula;
    private String carrera;

    public Estudiante(String info) {
        String[] info_split = info.substring(1, info.length() -1).split(",");
        this.matricula = info_split[0];
        this.carrera = info_split[1];
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
