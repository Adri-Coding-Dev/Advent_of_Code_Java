package year_2015;

import java.io.IOException;

import core.Day;
import utils_2015.Utils_Day01;

/**
 * DAY 1 2015
 */
public class Day_01_2015 extends Day {
    // Atributes
    private final int year = 2015; // Year number
    private final int day = 1; // Day number

    public Day_01_2015() {
        super(2015, 1);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day01.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day01.solvePart2(year, day));
    }
}