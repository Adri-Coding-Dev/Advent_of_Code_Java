package year_2025;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import utils.Utils;

import core.Day;

public class Day_03_2025 extends Day{
	private String inputPath="src/inputs_2025/Input_Day03.txt";
	public Day_03_2025() {
		super(2025,3);
	}

	@Override
	public String solvePart1() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String line;
        long totalSum = 0;
        
        // Procesar cada línea (banco de baterías)
        try {
			while ((line = br.readLine()) != null) {
			    int maxJoltage = Utils.getMaxJoltage(line);
			    totalSum += maxJoltage;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Voltaje Total del Banco: " + totalSum);
        return String.valueOf(totalSum);
	}

	@Override
	public String solvePart2() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String line;
        long totalSum=0;
        final int K=12;  //Número de baterías a seleccionar
        
        try {
			while ((line = br.readLine()) != null) {
			    totalSum += Utils.getMaxJoltage(line, K);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Voltaje Total del Bando: " + totalSum);
		return String.valueOf(totalSum);
	}

}
