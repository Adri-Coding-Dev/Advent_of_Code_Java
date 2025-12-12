package core;
import java.util.HashMap;
import java.util.Map;
public abstract class Calendar {
	
	protected final int year;
	protected final Map<Integer,Day>days;
	
	/**
	 * 
	 * @param year
	 */
	public Calendar(int year) {
		this.year=year;
		this.days=new HashMap<>();
		inicializeDays();
	}
	
	/**
	 * 
	 */
	protected abstract void inicializeDays();
	
	
	public void runDay(int dayNumber) {
		if(days.containsKey(dayNumber)) {
			Day day=days.get(dayNumber);
			System.out.println("\n"+"=".repeat(60));
			System.out.printf("🎄 Advent of Code %d - Day %02d 🎄 \n",year,dayNumber);
			System.out.println("=".repeat(60));
			day.run();
		}else {
			System.out.printf("Day %d not implemented for the year %d\n",dayNumber,year);
		}
	}
	
	
	public void runYear() {
		System.out.println("\n"+"=".repeat(60));
		System.out.printf("Starting Advent of Code %d \n",year);
		System.out.println("=".repeat(60));
		
		days.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->{
			runDay(entry.getKey());
		});
		
		System.out.println("=".repeat(60));
		System.out.printf("ADVENT OF CODE %d COMPLETED \n",year);
		System.out.println("=".repeat(60));
	}
	protected void addDay(int number,Day day) {
		days.put(number,day);
	}
	
	public int getYear() {
		return year;
	}
}
