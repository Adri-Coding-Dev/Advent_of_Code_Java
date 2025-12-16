package years;

import core.Calendar;
import year_2025.Day_01_2025;
import year_2025.Day_02_2025;
import year_2025.Day_03_2025;
import year_2025.Day_04_2025;
import year_2025.Day_05_2025;
import year_2025.Day_06_2025;
import year_2025.Day_07_2025;
import year_2025.Day_08_2025;
import year_2025.Day_09_2025;
import year_2025.Day_10_2025;
import year_2025.Day_11_2025;
import year_2025.Day_12_2025;

/**
 * Class Year (to add all days of this year)
 */
public class Year_2025 extends Calendar {

	/**
	 * Constructor
	 */
	public Year_2025() {
		super(2025);
	}

	/**
	 * Method to inicialize each Day
	 */
	@Override
	protected void inicializeDays() {
		addDay(1, new Day_01_2025());
		addDay(2, new Day_02_2025());
		addDay(3, new Day_03_2025());
		addDay(4, new Day_04_2025());
		addDay(5, new Day_05_2025());
		addDay(6, new Day_06_2025());
		addDay(7, new Day_07_2025());
		addDay(8, new Day_08_2025());
		addDay(9, new Day_09_2025());
		addDay(10, new Day_10_2025());
		addDay(11, new Day_11_2025());
		addDay(12, new Day_12_2025());

	}

}
