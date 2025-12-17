package core;

import years.Year_2015;
import years.Year_2025;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	// Advent of Code in Java - Project by Coding With Adri
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);

		// Options to choose
		System.out.println("🎄 ADVENT OF CODE RUNNER 🎄");
		System.out.println("1. Run entire year");
		System.out.println("2. Run specific day");
		System.out.print("Select option: ");

		// Save the option
		int option = s.nextInt();

		System.out.print("Enter year (2025/2024...): ");
		int year = s.nextInt();

		// Create a new Calendar with the year selected
		Calendar calendar = createCalendar(year);

		if (option == 1) {
			// If you select the first option, you can see all days of these year
			calendar.runYear();
		} else if (option == 2) {
			// If you select the second option, you can select too the day of year
			System.out.print("Enter day number (1-25): ");
			int day = s.nextInt();
			calendar.runDay(day);
		}

		s.close();

	}

	/**
	 * Method to create a new Calendar
	 * 
	 * @param year -> The number of year that you want to use for the AoC
	 * @return -> A new Calendar with all days of year selected
	 */
	private static Calendar createCalendar(int year) {
		switch (year) {
			case 2025:
				return new Year_2025();
			case 2015:
				return new Year_2015();
			default:
				throw new IllegalArgumentException("Year " + year + " not supported yet");
		}
	}

}
