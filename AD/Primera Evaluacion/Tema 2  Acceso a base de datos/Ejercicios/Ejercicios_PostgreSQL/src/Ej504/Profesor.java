package Ej504;

public class Profesor {
    private String cedula;
    private String departamento;

    public Profesor(String info) {
        String[] info_split = info.substring(1, info.length() -1).split(",");
        this.cedula = info_split[0];
        this.departamento = info_split[1];
    }

    public String getCedula() {
        return cedula;
    }


    public String getDepartamento() {
        return departamento;
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "cedula='" + cedula + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
