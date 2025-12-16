package year_2025;

import core.Day;
import utils_2025.Utils_Day04;

import java.io.IOException;

/**
 * DAY 4 of 2025 AoC
 */
public class Day_04_2025 extends Day {
    // Atributes
    private int year = 2025; // -> Year number
    private int day = 4; // -> Day Number

    public Day_04_2025() {
        super(2025, 4);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day04.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day04.solvePart2(year, day));
    }
}