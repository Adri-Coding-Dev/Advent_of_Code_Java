package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day03;

import java.io.IOException;
import java.util.List;

/**
 * DAY 3 of 2025 AoC
 */
public class Day_03_2025 extends Day {
	// Atributes
	private int year = 2025; // -> Year number
	private int day = 3; // -> Day number

	/**
	 * Constructor of Day
	 */
	public Day_03_2025() {
		super(2025, 3);
	}

	/**
	 * Method to solve the first Part of the problem
	 * Return IOException if the Utils.readInput can read de input
	 */
	@Override
	public String solvePart1() throws IOException {
		List<String> lines = Utils.readInput(year, day);
		long totalSum = 0;

		for (String line : lines) {
			if (line.isBlank())
				continue;
			int maxJoltage = Utils_Day03.getMaxJoltage(line);
			totalSum += maxJoltage;
		}

		return String.valueOf(totalSum);
	}

	/**
	 * Method to solve the second Part of the problem
	 * Return IOException if the Utils.readInput can read de input
	 */
	@Override
	public String solvePart2() throws IOException {
		List<String> lines = Utils.readInput(year, day);
		long totalSum = 0;
		final int K = 12;

		for (String line : lines) {
			if (line.isBlank())
				continue;
			totalSum += Utils_Day03.getMaxJoltage(line, K);
		}

		return String.valueOf(totalSum);
	}
}