package years;

import core.Calendar;
import year_2025.Day_01_2025;
import year_2025.Day_02_2025;
import year_2025.Day_03_2025;
import year_2025.Day_04_2025;
import year_2025.Day_05_2025;
public class Year_2025 extends Calendar{

	public Year_2025() {
		super(2025);		
	}

	@Override
	protected void inicializeDays() {
		addDay(1,new Day_01_2025());
		addDay(2,new Day_02_2025());
		addDay(3,new Day_03_2025());
		addDay(4,new Day_04_2025());
		addDay(5,new Day_05_2025());
		
	}

}
