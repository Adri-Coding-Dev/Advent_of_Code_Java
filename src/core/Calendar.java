package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Calendar {
	private int numeroDiasTotales = 25;
	private int numeroDiasCompletados = 0;
	protected final int year;
	protected final Map<Integer, Day> days;

	/**
	 * Constructor to create a new Calendar
	 * 
	 * @param year -> The number of AoC year
	 */
	public Calendar(int year) {
		this.year = year;
		this.days = new HashMap<>();
		inicializeDays();
	}

	/**
	 * Abstract method to inicialize all Days
	 */
	protected abstract void inicializeDays();

	/**
	 * Method to run a select day
	 * 
	 * @param dayNumber -> Number of day that you want to run
	 * @throws IOException -> If the day is not create or if the day is not
	 *                     implemented into the year
	 */
	public void runDay(int dayNumber) throws IOException {
		if (days.containsKey(dayNumber)) {
			Day day = days.get(dayNumber);
			System.out.println("\n" + "=".repeat(60));
			System.out.printf("🎄 Advent of Code %d - Day %02d 🎄 \n", year, dayNumber);
			System.out.println("=".repeat(60));
			numeroDiasCompletados++;
			day.run();
		} else {
			System.out.printf("Day %d not implemented for the year %d\n", dayNumber, year);
		}
	}

	/**
	 * Method to run a complete year (all days implemented into the year)
	 */
	public void runYear() {
		System.out.println("\n" + "=".repeat(60));
		System.out.printf("Starting Advent of Code %d \n", year);
		System.out.println("=".repeat(60));

		days.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
			try {
				runDay(entry.getKey());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		System.out.println("=".repeat(60));
		System.out.printf("ADVENT OF CODE %d COMPLETED %d/%d\n", year, numeroDiasCompletados, numeroDiasTotales);
		System.out.println("=".repeat(60));
	}

	/**
	 * Method to add a new Day (This is for use in the Year Class, to implement a
	 * Day)
	 * 
	 * @param number -> Number of Day
	 * @param day    -> Class of this Day (EX: Day_01_2025.java)
	 */
	protected void addDay(int number, Day day) {
		days.put(number, day);
	}

	/**
	 * Getter Method to get the year
	 * 
	 * @return -> Year
	 */
	public int getYear() {
		return year;
	}
}
