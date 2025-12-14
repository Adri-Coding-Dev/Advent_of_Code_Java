package year_2025;

import core.Day;
import utils_2025.Utils;
import utils_2025.Utils_Day02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * DAY 2 of 2025 AoC
 */
public class Day_02_2025 extends Day {
	// Atributes
	private int year = 2025; // -> Year number
	private int day = 2; // -> Day number

	/**
	 * Constructor of Day 2
	 */
	public Day_02_2025() {
		super(2025, 2);
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
			if (line.isBlank()) {
				continue;
			}

			String[] ranges = line.split(",");
			for (String range : ranges) {
				String[] parts = range.split("-");
				long L = Long.parseLong(parts[0]);
				long R = Long.parseLong(parts[1]);
				totalSum += Utils_Day02.sumInvalidInRange(L, R);
			}
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
		long total = 0;

		for (String line : lines) {
			if (line.isBlank()) {
				continue;
			}

			String[] rangesStr = line.split(",");
			List<long[]> ranges = new ArrayList<>();
			long globalMax = 0;
			for (String range : rangesStr) {
				String[] parts = range.split("-");
				long a = Long.parseLong(parts[0]);
				long b = Long.parseLong(parts[1]);
				ranges.add(new long[] { a, b });
				if (b > globalMax)
					globalMax = b;
			}

			TreeSet<Long> invalidSet = Utils_Day02.generateInvalidNumbers(globalMax);
			List<Long> invalidList = new ArrayList<>(invalidSet);

			for (long[] range : ranges) {
				long a = range[0];
				long b = range[1];
				int startIdx = Utils_Day02.lowerBound(invalidList, a);
				for (int i = startIdx; i < invalidList.size(); i++) {
					long num = invalidList.get(i);
					if (num > b)
						break;
					total += num;
				}
			}
		}
		return String.valueOf(total);
	}
}