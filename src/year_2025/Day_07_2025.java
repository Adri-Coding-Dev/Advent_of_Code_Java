package year_2025;

import java.io.IOException;

import core.Day;
import utils_2025.Utils_Day07;

/**
 * DAY 7 of 2025 AoC
 */
public class Day_07_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year number
    private final int day = 7; // -> Day Number

    public Day_07_2025() {
        super(2025, 7);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day07.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day07.solvePart2(year, day));
    }
}