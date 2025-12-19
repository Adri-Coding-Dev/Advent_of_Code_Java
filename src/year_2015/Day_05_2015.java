package year_2015;

import java.io.IOException;

import core.Day;

public class Day_05_2015 extends Day {
    private final int year = 2015;
    private final int day = 5;

    public Day_05_2015() {
        super(2015, 5);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(utils_2015.Utils_Day05.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(utils_2015.Utils_Day05.solvePart2(year, day));
    }

}
