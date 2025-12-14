package utils_2025;

public class Utils_Day01 {
    public static int moverDerecha(int cantidad, int puntero) {
        // Movimiento circular: sumas y haces módulo 100
        return (puntero + cantidad) % 100;
    }

    public static int moverIzquierda(int cantidad, int puntero) {
        // Para restar en un círculo: ((puntero - cantidad) % 100 + 100) % 100
        return ((puntero - cantidad) % 100 + 100) % 100;
    }
}
