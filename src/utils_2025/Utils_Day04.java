package utils_2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils_Day04 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        int[] rowOffsets = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colOffsets = { -1, 0, 1, -1, 1, -1, 0, 1 };

        int accessibleCount = 0;

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

                    if (neighborCount < 4) {
                        accessibleCount++;
                    }
                }
            }
        }

        return accessibleCount;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        int[] rowOffsets = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colOffsets = { -1, 0, 1, -1, 1, -1, 0, 1 };

        int totalRemoved = 0;

        while (true) {
            List<int[]> toRemove = new ArrayList<>();

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

                        if (neighborCount < 4) {
                            toRemove.add(new int[] { r, c });
                        }
                    }
                }
            }

            if (toRemove.isEmpty()) {
                break;
            }

            for (int[] pos : toRemove) {
                grid[pos[0]][pos[1]] = '.';
            }

            totalRemoved += toRemove.size();
        }

        return totalRemoved;
    }
}
