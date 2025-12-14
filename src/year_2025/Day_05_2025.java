package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day05;

import java.io.IOException;
import java.util.List;

public class Day_05_2025 extends Day {
	private int year = 2025;
	private int day = 5;

	public Day_05_2025() {
		super(2025, 5);
	}

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