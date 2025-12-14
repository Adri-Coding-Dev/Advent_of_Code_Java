package year_2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils_2025.Utils;
import utils_2025.Utils_Day08;
import core.Day;

public class Day_08_2025 extends Day {
    private final int year = 2025;
    private final int day = 8;

    public Day_08_2025() {
        super(2025, 8);
    }

    @Override
    public String solvePart1() throws IOException {
        List<String> lines = Utils.readInput(year, dayNumber);
        int N = lines.size();
        int[] x = new int[N];
        int[] y = new int[N];
        int[] z = new int[N];

        // Parsear coordenadas de las cajas de conexión desde el input
        // Parse junction box coordinates from input
        for (int i = 0; i < N; i++) {
            String[] parts = lines.get(i).split(",");
            x[i] = Integer.parseInt(parts[0]);
            y[i] = Integer.parseInt(parts[1]);
            z[i] = Integer.parseInt(parts[2]);
        }

        // Generar todos los pares posibles de cajas con sus distancias al cuadrado
        // Generate all possible pairs of boxes with their squared distances
        // Usamos distancia al cuadrado para evitar cálculos de raíz cuadrada
        // innecesarios
        // We use squared distance to avoid unnecessary square root calculations
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long dx = x[i] - x[j];
                long dy = y[i] - y[j];
                long dz = z[i] - z[j];
                long distSQ = dx * dx + dy * dy + dz * dz;
                pairs.add(new Pair(i, j, distSQ));
            }
        }

        // Ordenar los pares por distancia (más cercanos primero)
        // Sort pairs by distance (closest first)
        // Esto nos permite procesar las conexiones más cortas primero
        // This allows us to process the shortest connections first
        Collections.sort(pairs);

        // Inicializar estructura Union-Find para gestionar circuitos
        // Initialize Union-Find structure to manage circuits
        // Cada caja comienza como su propio circuito independiente
        // Each box starts as its own independent circuit
        int[] parent = new int[N];
        int[] size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Realizar conexiones: conectar los 1000 pares más cercanos que unan circuitos
        // diferentes
        // Make connections: connect the 1000 closest pairs that join different circuits
        int connections = 0;
        for (Pair p : pairs) {
            int rootI = Utils_Day08.find(p.i, parent);
            int rootJ = Utils_Day08.find(p.j, parent);

            // Solo conectar si las cajas están en circuitos diferentes
            // Only connect if the boxes are in different circuits
            if (rootI != rootJ) {
                // Unir los circuitos usando unión por tamaño para mantener el árbol balanceado
                // Merge circuits using union by size to keep the tree balanced
                if (size[rootI] < size[rootJ]) {
                    parent[rootI] = rootJ;
                    size[rootJ] += size[rootI];
                } else {
                    parent[rootJ] = rootI;
                    size[rootI] += size[rootJ];
                }
                connections++; // Incrementar contador de conexiones exitosas
                               // Increment successful connections counter

                // Detener después de 1000 conexiones exitosas
                // Stop after 1000 successful connections
                if (connections == 1000) {
                    break;
                }
            }
        }

        // Recolectar tamaños de todos los circuitos (solo las raíces del Union-Find)
        // Collect sizes of all circuits (only the Union-Find roots)
        List<Integer> circuitSizes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (parent[i] == i) { // Una raíz representa un circuito completo
                                  // A root represents a complete circuit
                circuitSizes.add(size[i]);
            }
        }

        // Ordenar tamaños de circuitos de mayor a menor
        // Sort circuit sizes from largest to smallest
        circuitSizes.sort(Collections.reverseOrder());

        // Calcular el producto de los tres circuitos más grandes
        // Calculate the product of the three largest circuits
        // Inicializar en 1 porque multiplicaremos
        // Initialize to 1 because we will multiply
        long result = 1;
        for (int i = 0; i < Math.min(3, circuitSizes.size()); i++) {
            result *= circuitSizes.get(i);
        }

        return String.valueOf(result);
    }

    @Override
    public String solvePart2() throws IOException {
        List<String> lines = Utils.readInput(year, dayNumber);
        int N = lines.size();
        int[] x = new int[N];
        int[] y = new int[N];
        int[] z = new int[N];

        // Parsear coordenadas de las cajas de conexión
        // Parse junction box coordinates
        for (int i = 0; i < N; i++) {
            String[] parts = lines.get(i).split(",");
            x[i] = Integer.parseInt(parts[0]);
            y[i] = Integer.parseInt(parts[1]);
            z[i] = Integer.parseInt(parts[2]);
        }

        // Generar todos los pares posibles de cajas con sus distancias al cuadrado
        // Generate all possible pairs of boxes with their squared distances
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long dx = x[i] - x[j];
                long dy = y[i] - y[j];
                long dz = z[i] - z[j];
                long distSQ = dx * dx + dy * dy + dz * dz;
                pairs.add(new Pair(i, j, distSQ));
            }
        }

        // Ordenar los pares por distancia (más cercanos primero)
        // Sort pairs by distance (closest first)
        Collections.sort(pairs);

        // Inicializar estructura Union-Find: cada caja es su propio circuito
        // Initialize Union-Find structure: each box is its own circuit
        int[] parent = new int[N];
        int[] size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Inicializar contador de componentes y variables para la última conexión
        // Initialize component counter and variables for the last connection
        int components = N; // Al principio, cada caja es un circuito independiente
                            // Initially, each box is an independent circuit
        int lastI = -1, lastJ = -1; // Índices de la última conexión exitosa
                                    // Indices of the last successful connection

        // Procesar pares en orden de distancia creciente
        // Process pairs in increasing distance order
        for (Pair p : pairs) {
            int rootI = Utils_Day08.find(p.i, parent);
            int rootJ = Utils_Day08.find(p.j, parent);

            // Solo conectar si están en circuitos diferentes
            // Only connect if they are in different circuits
            if (rootI != rootJ) {
                // Unir los dos circuitos (unión por tamaño para eficiencia)
                // Merge the two circuits (union by size for efficiency)
                if (size[rootI] < size[rootJ]) {
                    parent[rootI] = rootJ;
                    size[rootJ] += size[rootI];
                } else {
                    parent[rootJ] = rootI;
                    size[rootI] += size[rootJ];
                }

                // Guardar los índices de esta conexión exitosa
                // Save the indices of this successful connection
                lastI = p.i;
                lastJ = p.j;
                components--; // Reducir el número de circuitos independientes
                              // Reduce the number of independent circuits

                // Verificar si ya tenemos un solo circuito
                // Check if we already have a single circuit
                if (components == 1) {
                    // ¡Todas las cajas están conectadas! Esta fue la última conexión necesaria
                    // All boxes are connected! This was the last needed connection
                    break;
                }
            }
        }

        // Multiplicar las coordenadas X de la última conexión que unificó todos los
        // circuitos
        // Multiply the X coordinates of the last connection that unified all circuits
        // Usamos long para evitar desbordamiento de enteros
        // We use long to avoid integer overflow
        long result = (long) x[lastI] * (long) x[lastJ];
        return String.valueOf(result);
    }

    // Clase auxiliar para representar pares de cajas con su distancia
    // Helper class to represent pairs of boxes with their distance
    static class Pair implements Comparable<Pair> {
        int i, j; // Índices de las cajas / Indices of the boxes
        long distSQ; // Distancia al cuadrado entre las cajas / Squared distance between boxes

        Pair(int i, int j, long distSQ) {
            this.i = i;
            this.j = j;
            this.distSQ = distSQ;
        }

        // Comparar por distancia para ordenar
        // Compare by distance for sorting
        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.distSQ, other.distSQ);
        }
    }
}
