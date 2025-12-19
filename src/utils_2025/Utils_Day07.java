package utils_2025;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import utils.Utils;

public class Utils_Day07 {
    private static char[][] map;
    private static int width, height;
    private static BigInteger[][] memo;

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
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

        return splits;
    }

    public static BigInteger solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
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
            System.out.println("No solution or error");
            return null;
        }

        // Start memorization
        Utils_Day07.initializeMemo();

        // Conunt temporals lines
        BigInteger timelines = Utils_Day07.countTimelines(startPos[0], startPos[1]);
        return timelines;
    }

    /**
     * Sets the grid map for timeline calculations.
     *
     * @param mapArray -> the 2D character array representing the grid
     */
    public static void setMap(char[][] mapArray) {
        map = mapArray;
    }

    /**
     * Sets the dimensions of the grid.
     *
     * @param w -> the width of the grid
     * @param h -> the height of the grid
     */
    public static void setDimensions(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * Finds the starting position (marked 'S') in the grid.
     *
     * @param startPos -> an array of length 2 where coordinates will be stored
     *                 startPos[0] = x-coordinate, startPos[1] = y-coordinate
     *                 If 'S' is not found, both coordinates are set to -1
     */
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

    /*
     * Initializes the memoization array with null values.
     */
    public static void initializeMemo() {
        memo = new BigInteger[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                memo[y][x] = null;
            }
        }
    }

    /**
     * Recursively counts the number of timelines starting from position (x, y).
     * Uses memoization to avoid redundant calculations.
     * When encountering a splitter ('^'), the timeline splits into left and right
     * paths.
     *
     * @param x -> the current x-coordinate
     * @param y -> the current y-coordinate
     * @return -> the number of timelines from (x, y) to the bottom of the grid
     */
    public static BigInteger countTimelines(int x, int y) {
        // Check boundaries
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return BigInteger.ZERO;
        }

        // Return memoized result if available
        if (memo[y][x] != null) {
            return memo[y][x];
        }

        // If at the bottom row, there's exactly one timeline (exits the manifold)
        if (y == height - 1) {
            memo[y][x] = BigInteger.ONE;
            return memo[y][x];
        }

        BigInteger result = BigInteger.ZERO;

        // Particle always tries to move down first
        if (y + 1 < height && map[y + 1][x] == '^') {
            // Splitter found: divides into two timelines
            // Moves left and right in the next row

            // Move left in the next row
            if (x > 0) {
                result = result.add(countTimelines(x - 1, y + 1));
            }
            // Move right in the next row
            if (x < width - 1) {
                result = result.add(countTimelines(x + 1, y + 1));
            }
        } else {
            // Empty cell or 'S': continues downward
            if (y + 1 < height) {
                result = result.add(countTimelines(x, y + 1));
            }
        }

        memo[y][x] = result;
        return result;
    }
}