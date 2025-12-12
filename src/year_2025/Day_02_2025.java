package year_2025;
import core.Day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
import utils.Utils;

public class Day_02_2025 extends Day{
	private String inputPath="src/inputs_2025/Input_Day02.txt";
	public Day_02_2025() {
		super(2025,2);
	}
	@Override
	public String solvePart1() {
		try {
			Scanner s=new Scanner(new File(inputPath));
			//Leer el archivo de entrada
			long totalSum = 0;
	        while(s.hasNextLine()) {
	        	String line=s.nextLine().trim();
	        	//Dividir los rangos por comas
		        String[] ranges = line.split(",");

		        //Procesar cada rango
		        for (String range : ranges) {
		            String[] parts = range.split("-");
		            long L = Long.parseLong(parts[0]);
		            long R = Long.parseLong(parts[1]);
		            totalSum += Utils.sumInvalidInRange(L, R);
		        }
	        }
	        
	        
	        return String.valueOf(totalSum);
		}catch(Exception e) {
			System.out.printf("Error loading de File input ",e);
		}
		return null;
	}
	
	@Override
	public String solvePart2() {
		
		try {
			Scanner s=new Scanner(new File(inputPath));
			while(s.hasNextLine()) {
				String line=s.nextLine().trim();
				String[] rangesStr = line.split(",");
		        List<long[]> ranges = new ArrayList<>();
		        long globalMax = 0;
		        for (String range : rangesStr) {
		            String[] parts = range.split("-");
		            long a = Long.parseLong(parts[0]);
		            long b = Long.parseLong(parts[1]);
		            ranges.add(new long[]{a, b});
		            if (b > globalMax) globalMax = b;
		        }
		     // Generar todos los números inválidos hasta globalMax y hasta 10 dígitos
		        TreeSet<Long> invalidSet = Utils.generateInvalidNumbers(globalMax);

		        // Convertir a lista ordenada
		        List<Long> invalidList = new ArrayList<>(invalidSet);

		        // Sumar para cada rango
		        long total = 0;
		        for (long[] range : ranges) {
		            long a = range[0];
		            long b = range[1];
		            int startIdx = Utils.lowerBound(invalidList, a);
		            for (int i = startIdx; i < invalidList.size(); i++) {
		                long num = invalidList.get(i);
		                if (num > b) break;
		                total += num;
		            }
		        }

				return String.valueOf(total);
			}
		}catch(Exception e) {
			System.out.printf("Error loading de File input ",e);
        }
		return null;
	}
}
