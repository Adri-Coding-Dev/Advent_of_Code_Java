package year_2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Utils;
import core.Day;

public class Day_06_2025 extends Day {

    private String inputPath = "src/inputs_2025/Input_Day06.txt";
    
    public Day_06_2025() {
        super(2025, 6);
    }

    @Override
    public String solvePart1() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            List<String> lines = br.lines().toList();
            br.close();
            
            // La última línea contiene los operadores
            String operatorsLine = lines.get(lines.size() - 1);
            int numProblems = operatorsLine.trim().split("\\s+").length;
            
            long total = 0;
            
            // Para cada problema
            for (int problemIdx = 0; problemIdx < numProblems; problemIdx++) {
                long result = 0;
                char operator = ' ';
                boolean firstNumber = true;
                
                // Para cada fila de números (excluyendo la última fila de operadores)
                for (int row = 0; row < lines.size() - 1; row++) {
                    String line = lines.get(row);
                    String[] numbers = line.trim().split("\\s+");
                    
                    if (problemIdx < numbers.length) {
                        int number = Integer.parseInt(numbers[problemIdx]);
                        
                        if (firstNumber) {
                            result = number;
                            firstNumber = false;
                        } else {
                            // El operador está en la última fila
                            operator = operatorsLine.trim().split("\\s+")[problemIdx].charAt(0);
                            if (operator == '+') {
                                result += number;
                            } else if (operator == '*') {
                                result *= number;
                            }
                        }
                    }
                }
                total += result;
            }
            
            return String.valueOf(total);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String solvePart2() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            List<String> lines = br.lines().toList(); //Guardamos las lineas en una Lista
            br.close();
            
            //Encontrar el ancho máximo
            int maxWidth = 0;
            for (String line : lines) {
                maxWidth = Math.max(maxWidth, line.length());
            }
            
            //Rellenar líneas para que tengan el mismo ancho
            List<String> paddedLines = new ArrayList<>();
            for (String line : lines) {
                paddedLines.add(String.format("%-" + maxWidth + "s", line));
            }
            
            int numRows = paddedLines.size();
            int numCols = maxWidth;
            
            //Crear una cuadrícula de caracteres
            char[][] grid = new char[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                grid[i] = paddedLines.get(i).toCharArray();
            }
            
            //La última fila contiene los operadores
            int lastRow = numRows - 1;
            long total = 0;
            
            //Recorrer de derecha a izquierda
            int col = numCols - 1;
            while (col >= 0) {
                //Saltar columnas completamente vacías
                while (col >= 0 && isColumnEmpty(grid, col)) {
                    col--;
                }
                
                if (col < 0) break;
                
                //Encontrar el grupo de columnas del problema actual
                List<Integer> problemCols = new ArrayList<>();
                while (col >= 0 && !isColumnEmpty(grid, col)) {
                    problemCols.add(col);
                    col--;
                }
                
                //Las columnas están en orden de derecha a izquierda, pero para construir
                //los números necesitamos procesarlas en ese mismo orden
                Collections.reverse(problemCols);
                
                //El operador está en la última fila de la primera columna del problema
                //(la más a la izquierda del grupo)
                int operatorCol = problemCols.get(0);
                char operator = grid[lastRow][operatorCol];
                
                //Construir los números del problema
                List<Long> numbers = new ArrayList<>();
                for (int problemCol : problemCols) {
                    StringBuilder numberStr = new StringBuilder();
                    //Leer de arriba a abajo (excluyendo la fila de operadores)
                    for (int row = 0; row < lastRow; row++) {
                        char c = grid[row][problemCol];
                        if (c != ' ') {
                            numberStr.append(c);
                        }
                    }
                    
                    if (numberStr.length() > 0) {
                        //Convertir el número (ya está en el orden correcto: dígito más significativo arriba)
                        numbers.add(Long.parseLong(numberStr.toString()));
                    }
                }
                
                //Calcular el resultado del problema
                long problemResult;
                if (operator == '+') {
                    problemResult = 0;
                    for (long num : numbers) {
                        problemResult += num;
                    }
                } else { // '*'
                    problemResult = 1;
                    for (long num : numbers) {
                        problemResult *= num;
                    }
                }
                
                total += problemResult;
            }
            
            return String.valueOf(total);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isColumnEmpty(char[][] grid, int col) {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][col] != ' ') {
                return false;
            }
        }
        return true;
    }
}