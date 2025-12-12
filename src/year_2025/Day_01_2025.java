package year_2025;
import core.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import utils.Utils;

public class Day_01_2025 extends Day{
	private String inputPath="src/inputs_2025/Input_Day01.txt";
	public Day_01_2025() {
		super(2025,1);
	}
	@Override
	public String solvePart1(){
		int puntero = 50;   //Empieza apuntando al 50
        int contador = 0;   //Veces que llega a 0

        try {
            //Lee el archivo con las instrucciones
            Scanner s = new Scanner(new File(inputPath));
            
            //Procesa todas las líneas del archivo
            while (s.hasNextLine()) {
                String linea = s.nextLine().trim();
                
                //Saltar líneas vacías (Si las hubiera)
                if (linea.isEmpty()) {
                    continue;
                }
                
                //Extraer dirección (primer carácter) y cantidad (resto)
                char direccion = linea.charAt(0);
                int cantidad = Integer.parseInt(linea.substring(1));
                
                
                if (direccion == 'L') {
                    puntero = Utils.moverIzquierda(cantidad, puntero);
                } 
                else if (direccion == 'R') {
                    puntero = Utils.moverDerecha(cantidad, puntero);
                } 
                else {
                    System.out.println("Error en la instrucción: " + linea);
                    continue;
                }
                
                
                //Contar cuántas veces queda en 0
                if (puntero == 0) {
                    contador++;
                }
            }
            
            s.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo 'instrucciones.txt'");
        } catch (NumberFormatException e) {
            System.out.println("Error: formato incorrecto en alguna instrucción");
        }
        
        System.out.println("=================================");
        System.out.println("            PARTE 1              ");
        System.out.println("=================================");
        System.out.println("Contador final de 0: " + contador);
		
		return String.valueOf(contador);
	}
	
	@Override
	public String solvePart2() {
		int puntero = 50;   //Empieza apuntando al 50
        long contador = 0;  //Veces que llega a 0 (Usamos long porque el número puede ser grande)

        try {
            Scanner s = new Scanner(new File(inputPath));
            
            //Procesa todas las líneas del archivo
            while (s.hasNextLine()) {
                String linea = s.nextLine().trim();
                
                //Saltar líneas vacías (si las hubiera)
                if (linea.isEmpty()) {
                    continue;
                }
                
                //Extraer dirección (primer carácter) y cantidad (resto)
                char direccion = linea.charAt(0);
                int cantidad = Integer.parseInt(linea.substring(1));
                
                int delta = (direccion == 'R') ? cantidad : -cantidad;
                
                //Calcular las posiciones extremas del movimiento (en línea recta)
                long inicio = puntero;
                long finLineal = inicio + delta;
                long min = Math.min(inicio, finLineal);
                long max = Math.max(inicio, finLineal);
                
                //Encontrar el primer múltiplo de 100 >= min
                long primerMultiplo = (long) Math.ceil(min / 100.0) * 100;
                //Encontrar el último múltiplo de 100 <= max
                long ultimoMultiplo = (long) Math.floor(max / 100.0) * 100;
                
                //Contar cuántos múltiplos de 100 hay en el intervalo [min, max]
                long cuentaEsteMovimiento = 0;
                if (primerMultiplo <= ultimoMultiplo) {
                    cuentaEsteMovimiento = (ultimoMultiplo - primerMultiplo) / 100 + 1;
                }
                
                //Restar el punto de inicio si es múltiplo de 100 
                if (puntero % 100 == 0) {
                    cuentaEsteMovimiento--;
                }
                
                //Sumar al contador total
                contador += cuentaEsteMovimiento;
                
                //Actualizar la posición final (con módulo 100)
                puntero = (int)((puntero + delta) % 100);
                if (puntero < 0) {
                    puntero += 100;
                }
            }
            
            s.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo 'Instrucciones.txt'");
            System.out.println("Asegúrate de que el archivo está en la raíz del proyecto");
        } catch (NumberFormatException e) {
            System.out.println("Error: formato incorrecto en alguna instrucción");
        }
        
        System.out.println("=================================");
        System.out.println("            PARTE 2              ");
        System.out.println("=================================");
        System.out.println("Contador final de 0: " + contador);
		return String.valueOf(contador);
	}
}
