package utils_2025;

import java.io.IOException;
import java.util.List;
import utils.Utils;

/**
 * Public Class to implement the Utils for Day 1 of AoC's 2025
 */
public class Utils_Day01 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int puntero = 50;
        int contador = 0;

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            char direccion = line.charAt(0);
            int cantidad = Integer.parseInt(line.substring(1));

            if (direccion == 'L') {
                puntero = Utils_Day01.moverIzquierda(cantidad, puntero);
            } else if (direccion == 'R') {
                puntero = Utils_Day01.moverDerecha(cantidad, puntero);
            }

            if (puntero == 0) {
                contador++;
            }
        }
        return contador;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int puntero = 50;
        long contador = 0;

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            char direccion = line.charAt(0);
            int cantidad = Integer.parseInt(line.substring(1));

            int delta = (direccion == 'R') ? cantidad : -cantidad;

            // Calculate the extremes positions of movement
            long inicio = puntero;
            long finLineal = inicio + delta;
            long min = Math.min(inicio, finLineal);
            long max = Math.max(inicio, finLineal);

            // Find the first multip of 100 >= min
            long primerMultiplo = (long) Math.ceil(min / 100.0) * 100;
            // Find the last multip of 100 <=max
            long ultimoMultiplo = (long) Math.floor(max / 100.0) * 100;

            // Count how many multip of 100 have in the intervale [min, max]
            long cuentaEsteMovimiento = 0;
            if (primerMultiplo <= ultimoMultiplo) {
                cuentaEsteMovimiento = (ultimoMultiplo - primerMultiplo) / 100 + 1;
            }

            // Minus 1 if the start point is multip of 100
            if (puntero % 100 == 0) {
                cuentaEsteMovimiento--;
            }

            // Sum total to counter
            contador += cuentaEsteMovimiento;

            // Update the final position
            puntero = (int) ((puntero + delta) % 100);
            if (puntero < 0) {
                puntero += 100;
            }
        }
        return contador;
    }

    public static int moverDerecha(int cantidad, int puntero) {
        // Circular movement: sums and do the %100
        return (puntero + cantidad) % 100;
    }

    public static int moverIzquierda(int cantidad, int puntero) {
        // For Reset in a Circle: ((pointer - amount) % 100 + 100) % 100
        return ((puntero - cantidad) % 100 + 100) % 100;
    }
}
