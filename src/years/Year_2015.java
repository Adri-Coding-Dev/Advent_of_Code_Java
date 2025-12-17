package years;

import core.Calendar;
import year_2015.Day_01_2015;

public class Year_2015 extends Calendar {

    public Year_2015() {
        super(2015);
    }

    @Override
    protected void inicializeDays() {
        addDay(1, new Day_01_2015());
    }

}
