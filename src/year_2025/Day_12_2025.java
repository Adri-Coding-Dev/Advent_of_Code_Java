package year_2025;

import java.io.IOException;

import core.Day;
import utils_2025.Utils_Day12;

/**
 * DAY 12 AoC
 */
public class Day_12_2025 extends Day {
    // Atributes
    private final int year = 2025; // -> Year number
    private final int day = 12; // -> Day number

    public Day_12_2025() {
        super(2025, 12);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day12.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return "ITS DONE!!!!";
    }
}