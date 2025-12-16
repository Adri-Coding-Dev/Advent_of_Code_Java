package year_2025;

import core.Day;
import utils_2025.Utils_Day02;

import java.io.IOException;

/**
 * DAY 2 of 2025 AoC
 */
public class Day_02_2025 extends Day {
	// Atributes
	private int year = 2025; // -> Year number
	private int day = 2; // -> Day number

	public Day_02_2025() {
		super(2025, 2);
	}

	@Override
	public String solvePart1() throws IOException {
		return String.valueOf(Utils_Day02.solvePart1(year, day));
	}

	@Override
	public String solvePart2() throws IOException {
		return String.valueOf(Utils_Day02.solvePart2(year, day));
	}
}