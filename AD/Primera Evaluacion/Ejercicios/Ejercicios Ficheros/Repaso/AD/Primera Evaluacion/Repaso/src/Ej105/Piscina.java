package Ej105;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Piscina {
    private double longitudPiscina;
    private double anchuraPiscina;
    private double longitudParcela;
    private double anchuraParcela;
    private int aforo;
    private String[][] reservasPorFranja; // Matriz para gestionar reservas

    // Constructor
    public Piscina() {
    }

    // Métodos para pedir datos
    public void pedirDatos() {
        Scanner teclado = new Scanner(System.in);

        // Pedir longitud de la piscina
        longitudPiscina = solicitarValor(teclado, "longitud de la piscina");

        // Pedir anchura de la piscina
        anchuraPiscina = solicitarValor(teclado, "anchura de la piscina");

        // Pedir longitud de la parcela
        longitudParcela = solicitarValor(teclado, "longitud de la parcela");

        // Pedir anchura de la parcela
        anchuraParcela = solicitarValor(teclado, "anchura de la parcela");

        // Calcular aforo
        aforo = calcularAforo();
        reservasPorFranja = new String[6][aforo]; // 6 franjas horarias con el aforo máximo calculado

        System.out.println("El aforo de la piscina es: " + aforo + " personas.");
    }

    // Método para calcular el aforo
    public int calcularAforo() {
        // Calcular superficies
        double superficiePiscina = longitudPiscina * anchuraPiscina;
        double superficieParcela = longitudParcela * anchuraParcela;

        // Calcular aforo (2 metros cuadrados por persona)
        int aforoPiscina = (int) (superficiePiscina / 2);
        int aforoParcela = (int) (superficieParcela / 2);

        // Devolver el menor valor entre ambos aforos
        return Math.min(aforoPiscina, aforoParcela);
    }

    // Método auxiliar para pedir valores con manejo de excepciones
    private double solicitarValor(Scanner teclado, String descripcion) {
        double valor = -1;
        boolean valorValido = false;

        while (!valorValido) {
            try {
                System.out.println("Introduzca " + descripcion + ":");
                valor = teclado.nextDouble();
                valorValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Ha ocurrido una excepción. Solo se permiten números.");
                teclado.next(); // Limpiar el buffer del scanner
            }
        }
        return valor;
    }

    // Método para reservar plaza en una franja horaria
    public void reservar() {
        Scanner teclado = new Scanner(System.in);
        boolean reservaValida = false;

        while (!reservaValida) {
            // Pedir número de franja horaria
            int franja = solicitarFranja(teclado);

            // Verificar si hay plazas disponibles en la franja
            if (hayPlazasDisponibles(franja)) {
                // Pedir el DNI
                String dni = solicitarDNI(teclado);

                // Verificar si el DNI ya ha hecho una reserva en alguna franja
                if (dniYaReservado(dni)) {
                    System.out.println("No se puede reservar 2 veces en el mismo día.");
                } else {
                    // Realizar la reserva si el DNI es válido y no ha reservado antes
                    realizarReserva(franja, dni);
                    System.out.println("Su reserva se ha realizado con éxito.");
                    reservaValida = true;
                }
            } else {
                System.out.println("No hay plazas en la franja: " + (franja + 1));
            }
        }
    }

    // Método para solicitar una franja horaria válida
    private int solicitarFranja(Scanner teclado) {
        int franja = -1;
        while (franja < 0 || franja > 5) {
            try {
                System.out.println("Introduzca número de franja donde quiere reservar [1-6]:");
                franja = teclado.nextInt() - 1; // Convertir de [1-6] a índice de array [0-5]
                if (franja < 0 || franja > 5) {
                    System.out.println("Solo se permiten enteros entre [1,6]");
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten enteros entre [1,6]");
                teclado.next(); // Limpiar el buffer del scanner
            }
        }
        return franja;
    }

    // Método para solicitar un DNI válido
    private String solicitarDNI(Scanner teclado) {
        String dni = "";
        boolean dniValido = false;

        while (!dniValido) {
            System.out.println("Introduzca dni:");
            dni = teclado.next();
            if (dni.length() == 9 && dni.substring(0, 8).matches("\\d+") && Character.isLetter(dni.charAt(8))) {
                dniValido = true;
            } else {
                System.out.println("El dni debe tener 8 dígitos y una letra.");
            }
        }

        return dni;
    }

    // Método para verificar si un DNI ya ha reservado en alguna franja
    private boolean dniYaReservado(String dni) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < aforo; j++) {
                if (dni.equals(reservasPorFranja[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método para verificar si hay plazas disponibles en una franja
    private boolean hayPlazasDisponibles(int franja) {
        for (int i = 0; i < aforo; i++) {
            if (reservasPorFranja[franja][i] == null) {
                return true;
            }
        }
        return false;
    }

    // Método para realizar una reserva
    private void realizarReserva(int franja, String dni) {
        for (int i = 0; i < aforo; i++) {
            if (reservasPorFranja[franja][i] == null) {
                reservasPorFranja[franja][i] = dni;
                break;
            }
        }
    }
}