package year_2025;

import java.io.IOException;
import java.util.*;
import core.Day;
import utils_2025.Utils_Day10;

public class Day_10_2025 extends Day {
    private final int year = 2025;
    private final int day = 10;

    public Day_10_2025() {
        super(2025, 10);
    }

    @Override
    public String solvePart1() throws IOException {
        return String.valueOf(Utils_Day10.solvePart1(year, day));
    }

    @Override
    public String solvePart2() throws IOException {
        return String.valueOf(Utils_Day10.solvePart2(year, day));
    }

    // ========== PART 1: LIGHTS (XOR) ==========
    private int solveLights(String line) {
        int bracketClose = line.indexOf(']');
        String diagram = line.substring(1, bracketClose);
        int n = diagram.length();
        int targetMask = 0;
        for (int i = 0; i < n; i++) {
            if (diagram.charAt(i) == '#') {
                targetMask |= (1 << i);
            }
        }

        List<Integer> buttons = new ArrayList<>();
        int idx = bracketClose + 1;
        while (idx < line.length() && line.charAt(idx) != '{') {
            if (line.charAt(idx) == '(') {
                int end = line.indexOf(')', idx);
                String content = line.substring(idx + 1, end);
                int mask = 0;
                if (!content.isBlank()) {
                    for (String s : content.split(",")) {
                        mask |= 1 << Integer.parseInt(s.trim());
                    }
                }
                buttons.add(mask);
                idx = end + 1;
            } else {
                idx++;
            }
        }

        // BFS over states
        int[] dist = new int[1 << n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        while (!q.isEmpty()) {
            int state = q.poll();
            for (int btn : buttons) {
                int next = state ^ btn;
                if (dist[next] > dist[state] + 1) {
                    dist[next] = dist[state] + 1;
                    q.add(next);
                }
            }
        }
        return dist[targetMask];
    }

    // ========== PART 2: JOLTAGE (ADDITIVE) ==========
    private int solveJoltage(String line) {
        // Parse joltage targets
        int braceOpen = line.indexOf('{');
        int braceClose = line.indexOf('}');
        String[] targetParts = line.substring(braceOpen + 1, braceClose).split(",");
        int m = targetParts.length;
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(targetParts[i].trim());
        }

        // Parse buttons
        int bracketClose = line.indexOf(']');
        String buttonsPart = line.substring(bracketClose + 1, braceOpen).trim();
        List<int[]> buttonList = new ArrayList<>();
        int idx = 0;
        while (idx < buttonsPart.length()) {
            if (buttonsPart.charAt(idx) == '(') {
                int end = buttonsPart.indexOf(')', idx);
                String content = buttonsPart.substring(idx + 1, end);
                if (content.trim().isEmpty()) {
                    buttonList.add(new int[0]);
                } else {
                    String[] indices = content.split(",");
                    int[] btn = new int[indices.length];
                    for (int i = 0; i < indices.length; i++) {
                        btn[i] = Integer.parseInt(indices[i].trim());
                    }
                    buttonList.add(btn);
                }
                idx = end + 1;
            } else {
                idx++;
            }
        }

        // Remove empty buttons (they have no effect)
        buttonList.removeIf(btn -> btn.length == 0);

        int n = buttonList.size();
        // Sort buttons by coverage (larger first) to improve pruning
        buttonList.sort((a, b) -> Integer.compare(b.length, a.length));

        // Precompute button coverage as boolean matrix for faster checks
        boolean[][] covers = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int idxBtn : buttonList.get(i)) {
                covers[i][idxBtn] = true;
            }
        }

        JoltageSolver solver = new JoltageSolver(targets, buttonList, covers);
        return solver.solve();
    }

    private static class JoltageSolver {
        private int[] targets;
        private List<int[]> buttons;
        private boolean[][] covers;
        private int m, n;
        private int best;

        public JoltageSolver(int[] targets, List<int[]> buttons, boolean[][] covers) {
            this.targets = targets;
            this.buttons = buttons;
            this.covers = covers;
            this.m = targets.length;
            this.n = buttons.size();
            this.best = Integer.MAX_VALUE;
        }

        public int solve() {
            int[] state = targets.clone();
            dfs(0, 0, state);
            return best;
        }

        private void dfs(int btnIdx, int presses, int[] state) {
            // Prune if current presses already exceed best found
            if (presses >= best)
                return;

            // If all buttons processed, check if all targets are met
            if (btnIdx == n) {
                for (int s : state)
                    if (s != 0)
                        return;
                best = presses;
                return;
            }

            // Calculate lower bound for remaining presses
            int maxDeficit = 0;
            for (int i = 0; i < m; i++) {
                if (state[i] > 0) {
                    // Check if any remaining button covers this counter
                    boolean covered = false;
                    for (int j = btnIdx; j < n; j++) {
                        if (covers[j][i]) {
                            covered = true;
                            break;
                        }
                    }
                    if (!covered)
                        return; // impossible to satisfy this counter
                    maxDeficit = Math.max(maxDeficit, state[i]);
                }
            }
            if (presses + maxDeficit >= best)
                return;

            // Determine maximum times we can press current button without exceeding any
            // counter
            int maxPresses = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (covers[btnIdx][i]) {
                    maxPresses = Math.min(maxPresses, state[i]);
                }
            }
            // Further limit by remaining budget
            if (maxPresses > best - presses - 1) {
                maxPresses = best - presses - 1;
            }

            // Try decreasing number of presses (larger values first to find good solutions
            // early)
            for (int k = maxPresses; k >= 0; k--) {
                // Apply button k times
                for (int i = 0; i < m; i++) {
                    if (covers[btnIdx][i]) {
                        state[i] -= k;
                    }
                }
                dfs(btnIdx + 1, presses + k, state);
                // Undo
                for (int i = 0; i < m; i++) {
                    if (covers[btnIdx][i]) {
                        state[i] += k;
                    }
                }
                // Additional pruning: if pressing 0 times, no need to explore further with this
                // button
                if (k == 0)
                    break;
            }
        }
    }
}