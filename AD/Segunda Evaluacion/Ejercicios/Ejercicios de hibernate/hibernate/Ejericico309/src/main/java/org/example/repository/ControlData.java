package org.example.repository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlData {
    public static String leerCodigo(Scanner teclado) {
        String codigo = null;
        boolean codigoValido = true;
        do {
            codigo = teclado.next();
            Pattern pat = Pattern.compile("[0-9]{3}[A-Z]{1}");
            Matcher mat = pat.matcher(codigo);
            if (mat.matches()) {
                codigoValido = true;
            } else {
                System.out.println("ERROR. El código debe cumplir el formato: 3 dígitos seguido de una letra mayúscula.");
                teclado.nextLine();
                codigoValido = false;
            }
        } while (!codigoValido);

        return codigo;

    }

    public static String leerFecha(Scanner teclado) {

        String fecha = null;
        boolean fechaValida = true;
        do {
            fecha = teclado.next();
            Pattern pat = Pattern.compile("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}");
            Matcher mat = pat.matcher(fecha);
            if (mat.matches()) {
                int dia = Integer.parseInt(Character.toString(fecha.charAt(8)) + Character.toString(fecha.charAt(9)));
                int mes = Integer.parseInt(Character.toString(fecha.charAt(5)) + Character.toString(fecha.charAt(6)));
                int ano = Integer.parseInt(Character.toString(fecha.charAt(0)) + Character.toString(fecha.charAt(1)) + Character.toString(fecha.charAt(2)) + Character.toString(fecha.charAt(3)));
                if (mes > 12 || mes < 1) {
                    fechaValida = false;
                } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                    if (dia > 31 || dia < 1) {
                        fechaValida = false;
                    } else {
                        fechaValida = true;
                    }
                } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    if (dia > 30 || dia < 1) {
                        fechaValida = false;
                    } else {
                        fechaValida = true;
                    }
                } else if (mes == 2) {
                    if (((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))) && (dia <= 29 && dia > 0)) {
                        fechaValida = true;
                    } else if (dia <= 28 && dia > 0) {
                        fechaValida = true;
                    } else {
                        fechaValida = false;
                    }
                }

            } else {
                fechaValida = false;
            }
            if (!fechaValida) {
                System.out.println("ERROR. No ha introducido una fecha válida.\nEscriba otra fecha. Formato: yyyy-mm-dd");
                teclado.nextLine();
            }

        } while (!fechaValida);

        return fecha;

    }

    public static String leerDni(Scanner teclado) {

        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        String dni = null;
        boolean dniValido = true;
        do {
            dni = teclado.next();
            dni = dni.toUpperCase();
            if (dni.length() != 9) {
                dniValido = false;
            } else {
                String dniNumero = "";
                for (int i = 0; i < 8; i++) {
                    dniNumero = dniNumero + dni.charAt(i);
                }
                Pattern pat = Pattern.compile("[0-9]{8}");
                Matcher mat = pat.matcher(dniNumero);
                if (!mat.matches()) {
                    dniValido = false;
                } else {
                    int numero = Integer.parseInt(dniNumero);
                    if (numero % 23 < 0 || numero % 23 > 22) {
                        dniValido = false;
                    } else if (letras[numero % 23] == dni.charAt(8)) {
                        dniValido = true;
                    }

                }

            }

            if (!dniValido) {
                System.out.println("ERROR. No ha introducido un DNI válido.");
                teclado.nextLine();

            }
        } while (!dniValido);

        return dni;

    }

    public static boolean rango(int l1, int l2, int op) {
        boolean enrango = true;
        if (op < l1 || op > l2) {
            enrango = false;
            System.out.println("\tERROR: debe introducir un valor dentro do rango de números posibles. "
                    + "\n\t\tVuelva a introducir un número: \n");
        }
        return enrango;
    }

    public static byte leerByte(Scanner teclado) {

        byte valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextByte();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca un número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;

    }

    public static short leerShort(Scanner teclado) {
        short valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextShort();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca un número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static int leerInt(Scanner teclado) {
        int valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca un número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static long leerLong(Scanner teclado) {
        long valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextLong();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca otro número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static float leerFloat(Scanner teclado) {
        float valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextFloat();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca otro número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static double leerDouble(Scanner teclado) {
        double valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextDouble();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca otro número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static boolean leerBoolean(Scanner teclado) {
        boolean valor = false;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextBoolean();
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca otro 'true' o 'false': ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }

    public static String leerString(Scanner teclado) {
        String resultado = null;

        do {
            resultado = teclado.nextLine();
        } while (resultado.isEmpty());

        return resultado;
    }

    public static char leerChar(Scanner teclado) {
        String resultado = null;

        do {
            resultado = teclado.nextLine();
        } while (resultado.isEmpty());

        return resultado.charAt(0);
    }

    public static char leerLetra(Scanner teclado) {
        char caracter = '\0';

        do {
            caracter = (teclado.nextLine()).charAt(0);
        } while (!Character.isLetter(caracter));

        return caracter;
    }

    public static String leerNome(Scanner teclado) {
        String nome = null;
        boolean repetir = true;

        do {
            nome = teclado.nextLine();
            if (nome.isEmpty() || !nome.toUpperCase().matches("[A-ZÁÉÍÓÚÜÑ\\-\\s]*")) {
                System.out.println("\tERROR: debe introducir algún nombre válido "
                        + "\n\t\tVuelva a introducir: ");
            } else {
                repetir = false;
            }
        } while (repetir);

        return nome;
    }

    public static int leerDia(Scanner teclado) {
        int valor = 0;
        boolean correcto;

        do {
            correcto = true;
            try {
                valor = teclado.nextInt();
                if (valor < 1 || valor > 31) {
                    System.out.println("ERROR. No ha introducido un valor válido. Introduzca un número entre 1 y 31: ");
                    correcto = false;
                }
            } catch (Exception e) {
                System.out.println("ERROR. No ha introducido un valor válido. Introduzca un número: ");
                teclado.next();
                correcto = false;
            }
        } while (!correcto);

        return valor;
    }
}

