package years;

import core.Calendar;
import core.Day;
import year_2025.Day_01_2025;
public class Year_2025 extends Calendar{

	public Year_2025() {
		super(2025);		
	}

	@Override
	protected void inicializeDays() {
		addDay(1,new Day_01_2025());
		
	}

}
