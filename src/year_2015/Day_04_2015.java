package year_2015;

import java.io.IOException;

import core.Day;

public class Day_04_2015 extends Day {
    private final int year = 2015;
    private final int day = 4;

    public Day_04_2015() {
        super(2015, 4);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(utils_2015.Utils_Day04.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(utils_2015.Utils_Day04.solvePart2(year, day));
    }

}
