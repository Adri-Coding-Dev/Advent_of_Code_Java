package year_2025;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import core.Day;

public class Day_04_2025 extends Day{
	private final String filePath = "src/inputs_2025/Input_Day04.txt";

	public Day_04_2025() {
		super(2025,4);
	}

	@Override
	public String solvePart1() {
		try {
            // CORRECCIÓN: Usar la ruta correcta para dia_04
            // Si el archivo está en src/dia_04/Instrucciones.txt
            
            // Verificar si el archivo existe
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("ERROR: No se encuentra el archivo en: " + file.getAbsolutePath());
                System.out.println("Coloca Instrucciones.txt en: " + System.getProperty("user.dir"));
            }
            
            // 1. Leer el archivo Instrucciones.txt
            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            while ((line = br.readLine()) != null) {
                lines.add(line.trim()); // Usar trim() para eliminar espacios en blanco
            }
            br.close();
            
            // Verificar que se leyó algo
            if (lines.isEmpty()) {
                System.out.println("ERROR: El archivo está vacío o no se pudo leer.");
            }
            
            // 2. Convertir a matriz de caracteres
            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] grid = new char[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                grid[i] = lines.get(i).toCharArray();
            }
            
            // 3. Direcciones de los 8 vecinos
            int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
            
            // 4. Contar rollos accesibles
            int accessibleCount = 0;
            
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == '@') {
                        int neighborCount = 0;
                        
                        // Contar rollos adyacentes
                        for (int d = 0; d < 8; d++) {
                            int nr = r + rowOffsets[d];
                            int nc = c + colOffsets[d];
                            
                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                                if (grid[nr][nc] == '@') {
                                    neighborCount++;
                                }
                            }
                        }
                        
                        // Si tiene menos de 4 rollos adyacentes, es accesible
                        if (neighborCount < 4) {
                            accessibleCount++;
                        }
                    }
                }
            }
            
            // 5. Mostrar el resultado
            System.out.println();
            System.out.println("Solution: " + accessibleCount);
            return String.valueOf(accessibleCount);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public String solvePart2() {
		try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("ERROR: No se encuentra el archivo en: " + file.getAbsolutePath());
            }

            // Leer archivo
            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
            br.close();

            if (lines.isEmpty()) {
                System.out.println("ERROR: El archivo está vacío.");
            }

            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                grid[i] = lines.get(i).toCharArray();
            }

            // Direcciones de los 8 vecinos
            int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

            int totalRemoved = 0;

            while (true) {
                List<int[]> toRemove = new ArrayList<>();

                // Detectar rollos accesibles
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        if (grid[r][c] == '@') {

                            int neighborCount = 0;

                            for (int d = 0; d < 8; d++) {
                                int nr = r + rowOffsets[d];
                                int nc = c + colOffsets[d];

                                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                                    if (grid[nr][nc] == '@') {
                                        neighborCount++;
                                    }
                                }
                            }

                            // Si tiene menos de 4 vecinos -> accesible
                            if (neighborCount < 4) {
                                toRemove.add(new int[]{r, c});
                            }
                        }
                    }
                }

                // Si no hay nada que eliminar, fin
                if (toRemove.isEmpty()) {
                    break;
                }

                // Eliminar simultáneamente
                for (int[] pos : toRemove) {
                    grid[pos[0]][pos[1]] = '.';
                }

                totalRemoved += toRemove.size();
            }

            System.out.println("Final solution: " + totalRemoved);
            return String.valueOf(totalRemoved);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
		return null;
	}

}
