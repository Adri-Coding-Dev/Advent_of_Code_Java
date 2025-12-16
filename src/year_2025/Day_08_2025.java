package year_2025;

import java.io.IOException;

import utils_2025.Utils_Day08;
import core.Day;

/**
 * DAY 8 of 2025 AoC
 */
public class Day_08_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year number
    private final int day = 8; // -> Day number

    public Day_08_2025() {
        super(2025, 8);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day08.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day08.solvePart2(year, day));
    }
}