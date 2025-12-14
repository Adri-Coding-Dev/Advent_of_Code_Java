package utils_2025;

/**
 * Public Class to implement the Utils for Day 1 of AoC's 2025
 */
public class Utils_Day01 {

    public static int moverDerecha(int cantidad, int puntero) {
        // Circular movement: sums and do the %100
        return (puntero + cantidad) % 100;
    }

    public static int moverIzquierda(int cantidad, int puntero) {
        // For Reset in a Circle: ((pointer - amount) % 100 + 100) % 100
        return ((puntero - cantidad) % 100 + 100) % 100;
    }
}
