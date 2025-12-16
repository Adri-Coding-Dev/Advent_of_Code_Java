package year_2025;

import java.io.IOException;

import core.Day;
import utils_2025.Utils_Day06;

/**
 * DAY 6 of 2025 AoC
 */
public class Day_06_2025 extends Day {
    // Atributes
    private int year = 2025; // -> Year number
    private int day = 6; // -> Day Number

    public Day_06_2025() {
        super(2025, 6);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day06.solvePart1);
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day06.solvePart2(year, day));
    }
}