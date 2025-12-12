package core;
import years.Year_2025;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		
		 System.out.println("🎄 ADVENT OF CODE RUNNER 🎄");
	        System.out.println("1. Run entire year");
	        System.out.println("2. Run specific day");
	        System.out.print("Select option: ");
	        
	        int option = s.nextInt();
	        
	        System.out.print("Enter year (2025/2024...): ");
	        int year = s.nextInt();
	        
	        Calendar calendar = createCalendar(year);
	        
	        if (option == 1) {
	            calendar.runYear();
	        } else if (option == 2) {
	            System.out.print("Enter day number (1-25): ");
	            int day = s.nextInt();
	            calendar.runDay(day);
	        }
	        
	        s.close();
	    
	}
	private static Calendar createCalendar(int year) {
		switch(year) {
		case 2025:
			return new Year_2025();
		default:
			throw new IllegalArgumentException("Year "+year+" not supported yet");
		}
	}

}
