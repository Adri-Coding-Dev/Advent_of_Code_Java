package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day05;

import java.io.IOException;
import java.util.List;

/**
 * DAY 5 of 2025 AoC
 */
public class Day_05_2025 extends Day {
	// Atributes
	private int year = 2025; // -> Year number
	private int day = 5; // -> Day number

	/**
	 * Constructor of Day
	 */
	public Day_05_2025() {
		super(2025, 5);
	}

	/**
	 * Method to solve the first Part of the problem
	 * Return IOException if the Utils.readInput can read de input
	 */
	@Override
	public String solvePart1() throws IOException {
		List<String> lines = Utils.readInput(year, day);

		int freshCount = 0;
		for (String line : lines) {
			if (line.isBlank())
				continue;

			String[] parts = line.split(":");
			if (parts.length != 2)
				continue;

			String id = parts[0];
			String datesStr = parts[1];

			if (Utils_Day05.isFreshIngredient(id, datesStr)) {
				freshCount++;
			}
		}

		return String.valueOf(freshCount);
	}

	/**
	 * Method to solve the second Part of the problem
	 * Return IOException if the Utils.readInput can read de input
	 */
	@Override
	public String solvePart2() throws IOException {
		List<String> lines = Utils.readInput(year, day);
		long totalFreshIds = 0;

		for (String line : lines) {
			if (line.isBlank())
				continue;

			String[] parts = line.split(":");
			if (parts.length != 2)
				continue;

			String id = parts[0];
			String datesStr = parts[1];

			if (Utils_Day05.isFreshIngredient(id, datesStr)) {
				totalFreshIds += Long.parseLong(id);
			}
		}

		return String.valueOf(totalFreshIds);
	}

}