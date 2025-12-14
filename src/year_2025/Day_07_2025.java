package year_2025;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day07;

/**
 * DAY 7 of 2025 AoC
 */
public class Day_07_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year number
    private final int day = 7; // -> Day Number
    private char[][] map; // -> Matrix to carge the map
    private int width, height; // -> Height and width of Map

    /**
     * Constructor of Day
     */
    public Day_07_2025() {
        super(2025, 7);
    }

    /**
     * Method to solve the first Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
    @Override
    public String solvePart1() throws IOException {
        List<String> lines = Utils.readInput(year, dayNumber);
        height = lines.size();
        width = lines.get(0).length();

        // Convert the map in a 2D array
        map = new char[height][width];
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                map[y][x] = line.charAt(x);
            }
        }

        // Find the start position
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

        // Simulate the stormBreakers
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

    /**
     * Method to solve the second Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
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

        // Convert the map and dimensions for the Utils
        Utils_Day07.setMap(map);
        Utils_Day07.setDimensions(width, height);

        // Find the start position
        int[] startPos = new int[2];
        Utils_Day07.findStart(startPos);

        if (startPos[0] == -1 || startPos[1] == -1) {
            return "Start position not found";
        }

        // Start memorization
        Utils_Day07.initializeMemo();

        // Conunt temporals lines
        BigInteger timelines = Utils_Day07.countTimelines(startPos[0], startPos[1]);
        return timelines.toString();
    }
}