package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day01;

import java.io.IOException;
import java.util.List;

/**
 * DAY 1 of 2025 AoC
 */
public class Day_01_2025 extends Day {
    // Atributes
    private int year = 2025; // -> Number year
    private int day = 1; // -> Day number

    /**
     * Constructor of Day (Parent)
     */
    public Day_01_2025() {
        super(2025, 1);
    }

    /**
     * Method to solve the first Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
    @Override
    public String solvePart1() throws IOException {
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
        return String.valueOf(contador);
    }

    /**
     * Method to solve the second Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
    @Override
    public String solvePart2() throws IOException {
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
        return String.valueOf(contador);
    }
}