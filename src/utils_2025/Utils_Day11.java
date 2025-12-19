package utils_2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Utils;

public class Utils_Day11 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        return countPaths(lines);
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);

        // Construir el grafo
        Map<String, List<String>> graph = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            String device = parts[0].trim();
            List<String> outputs = new ArrayList<>();
            if (parts.length > 1) {
                for (String out : parts[1].trim().split(" ")) {
                    if (!out.isBlank())
                        outputs.add(out);
                }
            }
            graph.put(device, outputs);
        }

        // Memoización para optimizar subcaminos (usando un Map con claves de nodo y
        // nodos faltantes por visitar)
        Map<String, Map<Set<String>, Long>> memo = new HashMap<>();

        // Llamada inicial desde "svr" con los nodos obligatorios por visitar
        Set<String> required = new HashSet<>(Arrays.asList("dac", "fft"));
        return dfsPart2("svr", graph, required, memo);
    }

    private static long dfsPart2(String current, Map<String, List<String>> graph,
            Set<String> required, Map<String, Map<Set<String>, Long>> memo) {
        if (current.equals("out")) {
            // Camino válido solo si todos los required fueron visitados
            return required.isEmpty() ? 1 : 0;
        }

        // Crear un memo key con el conjunto de nodos pendientes
        Map<Set<String>, Long> nodeMemo = memo.computeIfAbsent(current, k -> new HashMap<>());
        if (nodeMemo.containsKey(required)) {
            return nodeMemo.get(required);
        }

        long totalPaths = 0;

        // Si current es uno de los nodos obligatorios, lo eliminamos de los pendientes
        Set<String> remaining = new HashSet<>(required);
        remaining.remove(current);

        for (String next : graph.getOrDefault(current, List.of())) {
            totalPaths += dfsPart2(next, graph, remaining, memo);
        }

        nodeMemo.put(required, totalPaths); // Guardar en memo
        return totalPaths;
    }

    private static long countPaths(List<String> lines) {
        // Construir el grafo
        Map<String, List<String>> graph = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            String device = parts[0].trim();
            List<String> outputs = new ArrayList<>();
            if (parts.length > 1) {
                for (String out : parts[1].trim().split(" ")) {
                    if (!out.isBlank())
                        outputs.add(out);
                }
            }
            graph.put(device, outputs);
        }

        // Memoización: nodo -> número de caminos desde ese nodo a "out"
        Map<String, Long> memo = new HashMap<>();

        // Llamada inicial desde "you"
        return dfs("you", graph, memo);
    }

    private static long dfs(String current, Map<String, List<String>> graph, Map<String, Long> memo) {
        if (current.equals("out")) {
            return 1; // hemos llegado a "out", un camino válido
        }

        if (memo.containsKey(current)) {
            return memo.get(current); // ya calculado previamente
        }

        long totalPaths = 0;
        for (String next : graph.getOrDefault(current, List.of())) {
            totalPaths += dfs(next, graph, memo);
        }

        memo.put(current, totalPaths); // guardar en memoización
        return totalPaths;
    }
}
