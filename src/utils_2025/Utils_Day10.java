package utils_2025;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Utils;

public class Utils_Day10 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long total = 0;
        for (String line : lines)
            if (!line.isBlank())
                total += solveLights(line);
        return total;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long total = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                total += solveJoltage(line);
            }
        }
        return total;
    }

    // ================= PART 1 =================

    private static int solveLights(String line) {
        int bc = line.indexOf(']');
        String diagram = line.substring(1, bc);
        int n = diagram.length();
        int target = 0;
        for (int i = 0; i < n; i++)
            if (diagram.charAt(i) == '#')
                target |= 1 << i;

        List<Integer> buttons = new ArrayList<>();
        int idx = bc + 1;
        while (line.charAt(idx) != '{') {
            if (line.charAt(idx) == '(') {
                int e = line.indexOf(')', idx);
                int mask = 0;
                String c = line.substring(idx + 1, e);
                if (!c.isBlank())
                    for (String s : c.split(","))
                        mask |= 1 << Integer.parseInt(s.trim());
                buttons.add(mask);
                idx = e + 1;
            } else
                idx++;
        }

        int[] dist = new int[1 << n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);

        while (!q.isEmpty()) {
            int s = q.poll();
            for (int b : buttons) {
                int ns = s ^ b;
                if (dist[ns] > dist[s] + 1) {
                    dist[ns] = dist[s] + 1;
                    q.add(ns);
                }
            }
        }
        return dist[target];
    }

    // ================= PART 2: JOLTAGE =================

    private static long solveJoltage(String line) {
        Parsed parsed = parseLine(line);
        int n = parsed.targets.length;
        int m = parsed.buttons.size();

        // Convertir targets de int[] a List<Integer>
        List<Integer> targetsList = new ArrayList<>();
        for (int v : parsed.targets)
            targetsList.add(v);

        // Generar matriz de influencia: buttonInfluences[botón][contador]
        boolean[][] buttonInfluences = new boolean[m][n];
        for (int j = 0; j < m; j++) {
            for (int idx : parsed.buttons.get(j)) {
                buttonInfluences[j][idx] = true;
            }
        }

        // Precalcular todos los patrones posibles de botones
        Map<List<Integer>, Integer> joltageIncrToNrPresses = new HashMap<>();
        int nrPatterns = 1 << m;
        for (int i = 0; i < nrPatterns; i++) {
            List<Integer> incr = new ArrayList<>(Collections.nCopies(n, 0));
            int presses = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    for (int k = 0; k < n; k++) {
                        if (buttonInfluences[j][k])
                            incr.set(k, incr.get(k) + 1);
                    }
                    presses++;
                }
            }
            if (!joltageIncrToNrPresses.containsKey(incr) || joltageIncrToNrPresses.get(incr) > presses) {
                joltageIncrToNrPresses.put(incr, presses);
            }
        }

        // Recursión con memoización
        return getNrOfPressesForVoltages(targetsList, joltageIncrToNrPresses, new HashMap<>());
    }

    private static long getNrOfPressesForVoltages(List<Integer> current, Map<List<Integer>, Integer> patterns,
            Map<List<Integer>, Long> memo) {
        boolean allZero = current.stream().allMatch(v -> v == 0);
        if (allZero)
            return 0;

        if (memo.containsKey(current))
            return memo.get(current);

        long result = Long.MAX_VALUE;
        for (Map.Entry<List<Integer>, Integer> entry : patterns.entrySet()) {
            List<Integer> pattern = entry.getKey();
            boolean valid = true;
            for (int i = 0; i < pattern.size(); i++) {
                int c = current.get(i);
                int p = pattern.get(i);
                if (!(p <= c && (c - p) % 2 == 0)) {
                    valid = false;
                    break;
                }
            }
            if (!valid)
                continue;

            List<Integer> nextGoal = new ArrayList<>();
            for (int i = 0; i < current.size(); i++) {
                nextGoal.add((current.get(i) - pattern.get(i)) / 2);
            }

            long sub = getNrOfPressesForVoltages(nextGoal, patterns, memo);
            if (sub != Long.MAX_VALUE) {
                result = Math.min(result, entry.getValue() + 2 * sub);
            }
        }

        memo.put(current, result);
        return result;
    }

    // ================= PARSING =================

    private static class Parsed {
        List<List<Integer>> buttons;
        int[] targets;
    }

    private static Parsed parseLine(String line) {
        Parsed p = new Parsed();
        p.buttons = new ArrayList<>();

        int brace = line.indexOf('{');
        String btnPart = line.substring(0, brace);
        String tgtPart = line.substring(brace + 1, line.length() - 1);

        for (int i = 0; i < btnPart.length(); i++) {
            if (btnPart.charAt(i) == '(') {
                int end = btnPart.indexOf(')', i);
                String content = btnPart.substring(i + 1, end);
                List<Integer> list = new ArrayList<>();
                if (!content.isBlank()) {
                    for (String s : content.split(",")) {
                        list.add(Integer.parseInt(s.trim()));
                    }
                }
                p.buttons.add(list);
                i = end;
            }
        }

        String[] t = tgtPart.split(",");
        p.targets = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            p.targets[i] = Integer.parseInt(t[i].trim());
        }

        return p;
    }
}
