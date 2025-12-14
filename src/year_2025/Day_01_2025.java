package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day01;

import java.io.IOException;
import java.util.List;

public class Day_01_2025 extends Day {
    private int year = 2025;
    private int day = 1;

    public Day_01_2025() {
        super(2025, 1);
    }

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

            // Calcular las posiciones extremas del movimiento
            long inicio = puntero;
            long finLineal = inicio + delta;
            long min = Math.min(inicio, finLineal);
            long max = Math.max(inicio, finLineal);

            // Encontrar el primer múltiplo de 100 >= min
            long primerMultiplo = (long) Math.ceil(min / 100.0) * 100;
            // Encontrar el último múltiplo de 100 <= max
            long ultimoMultiplo = (long) Math.floor(max / 100.0) * 100;

            // Contar cuántos múltiplos de 100 hay en el intervalo [min, max]
            long cuentaEsteMovimiento = 0;
            if (primerMultiplo <= ultimoMultiplo) {
                cuentaEsteMovimiento = (ultimoMultiplo - primerMultiplo) / 100 + 1;
            }

            // Restar el punto de inicio si es múltiplo de 100
            if (puntero % 100 == 0) {
                cuentaEsteMovimiento--;
            }

            // Sumar al contador total
            contador += cuentaEsteMovimiento;

            // Actualizar la posición final
            puntero = (int) ((puntero + delta) % 100);
            if (puntero < 0) {
                puntero += 100;
            }
        }
        return String.valueOf(contador);
    }
}