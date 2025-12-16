package year_2025;

import java.io.IOException;

import core.Day;
import utils_2025.Utils_Day11;

/**
 * DAY 10 of 2025
 */
public class Day_11_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year number
    private final int day = 11; // -> Day number

    public Day_11_2025() {
        super(2025, 11);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day11.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day11.solvePart2(year, day));
    }
}