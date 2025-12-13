package year_2025;
import java.io.BufferedReader;
import java.io.FileReader;
import utils.Utils;

import core.Day;

public class Day_06_2025 extends Day{

	private String inputPath = "src/inputs_2025/Input_Day06.txt";
	
	public Day_06_2025() {
		super(2025,6);
	}

	@Override
	public String solvePart1() {
		try {
		BufferedReader br = new BufferedReader(new FileReader(inputPath));
		Object[] lista = br.lines().toArray();
		int largoLista = Utils.contadorNumerosDia6(lista)/(lista.length - 1) + 1;
		char[] listaOperacion = new char[largoLista];
		int[][] listaNumeros = new int[lista.length - 1][largoLista];
		Utils.separadorNumerosDia6(lista, listaNumeros, listaOperacion);
		
		long resultado=0;
		long resultadoOperacionActual = 0;
		for(int i = 0; i < largoLista; i++) {
			if (listaOperacion[i] == '+') {
				resultadoOperacionActual = 0;
				for(int j = 0; j < listaNumeros.length; j++) {
					resultadoOperacionActual += listaNumeros[j][i];
				}
			} else {
				resultadoOperacionActual = 1;
				for(int j = 0; j < listaNumeros.length; j++) {
					resultadoOperacionActual *= listaNumeros[j][i];
				}
			}
			resultado += resultadoOperacionActual;
			resultadoOperacionActual = 0;
		}
		
		return String.valueOf(resultado);
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public String solvePart2() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputPath));
			Object[] lista = br.lines().toArray();
			int largoLista = Utils.contadorNumerosDia6(lista)/(lista.length - 1) + 1;
			char[] listaOperacion = new char[largoLista];
			int[][] listaNumeros = new int[lista.length - 1][largoLista];
			Utils.separadorNumerosDia6Parte2(lista, listaNumeros, listaOperacion);
			
			for(int c=0; c<listaNumeros.length; c++) {
				for(int w=0; w<listaNumeros[c].length; w++) {
					System.out.print(listaNumeros[c][w] + ", ");
				}
				System.out.println();
			}
			
			long resultado=0;
			long resultadoOperacionActual = 0;
			for(int i = 0; i < largoLista; i++) {
				if (listaOperacion[i] == '+') {
					resultadoOperacionActual = 0;
					for(int j = 0; j < listaNumeros.length; j++) {
						resultadoOperacionActual += listaNumeros[j][i];
					}
				} else {
					resultadoOperacionActual = 1;
					for(int j = 0; j < listaNumeros.length; j++) {
						resultadoOperacionActual *= listaNumeros[j][i];
					}
				}
				resultado += resultadoOperacionActual;
				resultadoOperacionActual = 0;
			}
			
			return String.valueOf(resultado);
			}catch(Exception e) {
				System.out.println(e);
			}
			return null;
	}

}