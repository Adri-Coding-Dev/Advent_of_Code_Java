package year_2025;

import core.Day;
import utils_2025.Utils_Day03;

import java.io.IOException;

/**
 * DAY 3 of 2025 AoC
 */
public class Day_03_2025 extends Day {
	// Atributes
	private int year = 2025; // -> Year number
	private int day = 3; // -> Day number

	public Day_03_2025() {
		super(2025, 3);
	}

	@Override
	public String solvePart1() throws IOException {
		return String.valueOf(Utils_Day03.solvePart1(year, day));
	}

	@Override
	public String solvePart2() throws IOException {
		return String.valueOf(Utils_Day03.solvePart2(year, day));
	}
}