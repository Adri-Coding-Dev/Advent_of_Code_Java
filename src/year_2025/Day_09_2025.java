package year_2025;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import utils_2025.Utils_Day09;
import utils_2025.Utils;
import core.Day;

/**
 * DAY 9 of 2025 AoC
 */
public class Day_09_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year Number
    private final int day = 9; // -> Day Number

    /**
     * Constructor of Day
     */
    public Day_09_2025() {
        super(2025, 9);
    }

    /**
     * Method to solve the first Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
    @Override
    public String solvePart1() throws IOException {
        Map<Integer, List<Integer>> filas = Utils.readInputMap(year, dayNumber);

        int result = Utils_Day09.maxRectangleArea(filas);
        return String.valueOf(result);
    }

    /**
     * Method to solve the second Part of the problem
     * Return IOException if the Utils.readInput can read de input
     */
    @Override
    public String solvePart2() throws IOException {

        return "Not implemented yet";
    }

}
