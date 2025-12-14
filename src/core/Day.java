package core;

import java.io.IOException;

public abstract class Day {
	/**
	 * Properties of this Class
	 * year -> Number of year (2025/2024...)
	 * day -> Number of day (1,2...)
	 */
	protected final int year;
	protected final int dayNumber;

	/**
	 * Constructor of Day
	 * 
	 * @param year      -> Number of Year
	 * @param dayNumber -> Number of Day
	 */
	public Day(int year, int dayNumber) {
		this.year = year;
		this.dayNumber = dayNumber;
	}

	/**
	 * Method to solve the first part of Day
	 * 
	 * @return -> The Solution (String.valueOf(Result))
	 * @throws IOException -> If The Input Day is not create
	 */
	public abstract String solvePart1() throws IOException;

	/**
	 * Method to solve the second part of Day
	 * 
	 * @return -> The Solution (String.valueOf(Result))
	 * @throws IOException -> If The Input Day is not create
	 */
	public abstract String solvePart2() throws IOException;

	/**
	 * Method to run a Day (both parts)
	 * 
	 * @throws IOException -> If the Input Day is not create
	 */
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

	/**
	 * Getter Method to get the Year
	 * 
	 * @return -> The Year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Getter Method to get the Number Day
	 * 
	 * @return -> The Day
	 */
	public int getNumber() {
		return dayNumber;
	}
}
