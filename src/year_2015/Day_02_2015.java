package year_2015;

import java.io.IOException;

import core.Day;

/**
 * DAY 2 AoC 2015
 */
public class Day_02_2015 extends Day {
    // Atributes
    private final int year = 2015;
    private final int day = 2;

    public Day_02_2015() {
        super(2015, 2);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(utils_2015.Utils_Day02.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return "Not implemented yet";
    }
}