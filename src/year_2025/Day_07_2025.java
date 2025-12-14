package year_2025;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day07;

public class Day_07_2025 extends Day {
    private final int year = 2025;
    private final int day = 7;
    private char[][] map;
    private int width, height;

    public Day_07_2025() {
        super(2025, 7);
    }

    @Override
    public String solvePart1() throws IOException {
        List<String> lines = Utils.readInput(year, dayNumber);
        height = lines.size();
        width = lines.get(0).length();

        // Convertir el mapa a array 2D
        map = new char[height][width];
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                map[y][x] = line.charAt(x);
            }
        }

        // Encontrar la posición de inicio
        int startX = -1, startY = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 'S') {
                    startX = x;
                    startY = y;
                    break;
                }
            }
            if (startX != -1)
                break;
        }

        // Simular los rayos
        boolean[][] beam = new boolean[height][width];
        beam[startY][startX] = true;
        int splits = 0;

        for (int y = startY; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                if (beam[y][x]) {
                    if (map[y + 1][x] == '^') {
                        splits++;
                        if (x > 0)
                            beam[y + 1][x - 1] = true;
                        if (x < width - 1)
                            beam[y + 1][x + 1] = true;
                    } else {
                        beam[y + 1][x] = true;
                    }
                }
            }
        }

        return String.valueOf(splits);
    }

    @Override
    public String solvePart2() throws IOException {
        List<String> lines = Utils.readInput(year, dayNumber);
        height = lines.size();
        width = lines.get(0).length();
        map = new char[height][width];

        // Cargar el mapa
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                map[y][x] = line.charAt(x);
            }
        }

        // Pasar el mapa y dimensiones a Utils_Day07
        Utils_Day07.setMap(map);
        Utils_Day07.setDimensions(width, height);

        // Encontrar la posición inicial
        int[] startPos = new int[2];
        Utils_Day07.findStart(startPos);

        if (startPos[0] == -1 || startPos[1] == -1) {
            return "Start position not found";
        }

        // Inicializar memoización
        Utils_Day07.initializeMemo();

        // Contar líneas temporales
        BigInteger timelines = Utils_Day07.countTimelines(startPos[0], startPos[1]);
        return timelines.toString();
    }
}