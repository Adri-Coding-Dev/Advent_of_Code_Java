package utils_2025;

import java.math.BigInteger;

public class Utils_Day07 {
    private static char[][] map;
    private static int width, height;
    private static BigInteger[][] memo;

    // Métodos para inicializar los datos desde la clase principal
    public static void setMap(char[][] mapArray) {
        map = mapArray;
    }

    public static void setDimensions(int w, int h) {
        width = w;
        height = h;
    }

    public static void findStart(int[] startPos) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 'S') {
                    startPos[0] = x;
                    startPos[1] = y;
                    return;
                }
            }
        }
        startPos[0] = -1;
        startPos[1] = -1;
    }

    public static void initializeMemo() {
        memo = new BigInteger[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                memo[y][x] = null;
            }
        }
    }

    public static BigInteger countTimelines(int x, int y) {
        // Verificar límites
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return BigInteger.ZERO;
        }

        // Si ya calculamos este estado, retornar memoizado
        if (memo[y][x] != null) {
            return memo[y][x];
        }

        // Si estamos en la última fila, solo hay una línea temporal (sale del manifold)
        if (y == height - 1) {
            memo[y][x] = BigInteger.ONE;
            return memo[y][x];
        }

        BigInteger result = BigInteger.ZERO;

        // La partícula siempre intenta moverse hacia abajo primero
        if (y + 1 < height && map[y + 1][x] == '^') {
            // Encuentra un splitter: se divide en dos líneas temporales
            // IMPORTANTE: Cuando encuentra un splitter, se mueve IZQUIERDA y DERECHA
            // en la MISMA fila, NO hacia abajo

            // Moverse a la izquierda en la misma fila
            if (x > 0) {
                result = result.add(countTimelines(x - 1, y + 1));
            }
            // Moverse a la derecha en la misma fila
            if (x < width - 1) {
                result = result.add(countTimelines(x + 1, y + 1));
            }
        } else {
            // Celda vacía o 'S': continúa hacia abajo
            if (y + 1 < height) {
                result = result.add(countTimelines(x, y + 1));
            }
        }

        memo[y][x] = result;
        return result;
    }
}