package years;

import core.Calendar;
import year_2015.*;

public class Year_2015 extends Calendar {

    public Year_2015() {
        super(2015);
    }

    @Override
    protected void inicializeDays() {
        addDay(1, new Day_01_2015());
        addDay(2, new Day_02_2015());
        addDay(3, new Day_03_2015());
        addDay(4, new Day_04_2015());
        addDay(5, new Day_05_2015());
        addDay(6, new Day_06_2015());
    }

}
