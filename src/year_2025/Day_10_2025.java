package year_2025;

import java.io.IOException;

import core.Day;
import utils_2025.Utils_Day10;

/**
 * AoC DAY 10
 */
public class Day_10_2025 extends Day {
    // Atributes
    private final int year = 2025;
    private final int day = 10;

    public Day_10_2025() {
        super(2025, 10);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day10.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day10.solvePart2(year, day));
    }
}