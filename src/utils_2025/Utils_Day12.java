package utils_2025;

import java.io.IOException;
import java.util.List;
import utils.Utils;

public class Utils_Day12 {
    private static final int[] AREA = { 5, 7, 7, 7, 7, 6 };

    private static final int[] MIN_H = { 3, 3, 3, 3, 3, 3 };
    private static final int[] MIN_W = { 3, 3, 3, 3, 3, 3 };

    private static final int[] MAX_ROW_FILL = { 2, 3, 3, 3, 3, 3 };
    private static final int[] MAX_COL_FILL = { 2, 3, 3, 3, 2, 2 };

    public static long solvePart1(int year, int day) throws IOException {

        List<String> lines = Utils.readInput(year, day);

        long solvedBoards = 0;

        for (String line : lines) {

            line = line.trim();
            if (line.isEmpty())
                continue;

            // FILTRO CORRECTO: solo líneas de tablero
            if (!line.contains("x") || !line.contains(":") || line.contains("#")) {
                continue;
            }

            // Ejemplo válido:
            // 46x47: 56 62 53 42 59 62
            String[] parts = line.split(":");
            String[] dims = parts[0].split("x");

            int h = Integer.parseInt(dims[0].trim());
            int w = Integer.parseInt(dims[1].trim());

            String[] qtyStr = parts[1].trim().split("\\s+");
            long[] qty = new long[6];

            for (int i = 0; i < 6; i++) {
                qty[i] = Long.parseLong(qtyStr[i]);
            }

            long boardArea = (long) h * w;
            long requiredArea = 0;

            boolean possible = true;

            for (int i = 0; i < 6; i++) {

                long q = qty[i];
                requiredArea += q * AREA[i];

                // Filtro 1: área total
                if (requiredArea > boardArea) {
                    possible = false;
                    break;
                }

                // Filtro 2: dimensiones mínimas
                if (h < MIN_H[i] && w < MIN_W[i]) {
                    possible = false;
                    break;
                }

                // Filtro 3: bounding-box packing
                long maxFit = (long) (h / MIN_H[i]) * (w / MIN_W[i]);
                if (q > maxFit) {
                    possible = false;
                    break;
                }

                // Filtro 4: densidad fila / columna
                if (q * MAX_ROW_FILL[i] > boardArea ||
                        q * MAX_COL_FILL[i] > boardArea) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                solvedBoards++;
            }
        }

        return solvedBoards;
    }

    public static int solvePart2(int year, int day) throws IOException {
        return 0;
    }
}