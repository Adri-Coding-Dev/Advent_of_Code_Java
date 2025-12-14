package core;

import java.io.IOException;

public abstract class Day {
	protected final int year;
	protected final int dayNumber;

	public Day(int year, int dayNumber) {
		this.year = year;
		this.dayNumber = dayNumber;
	}

	public abstract String solvePart1() throws IOException;

	public abstract String solvePart2() throws IOException;

	public void run() throws IOException {
		long startTime = System.currentTimeMillis();
		String result1 = solvePart1();
		String result2 = solvePart2();

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		System.out.printf("Part 1: %s\n", result1);
		System.out.printf("Part 2: %s\n", result2);
		System.out.printf("Time: %d ms\n", duration);
	}

	public int getYear() {
		return year;
	}

	public int getNumber() {
		return dayNumber;
	}
}
