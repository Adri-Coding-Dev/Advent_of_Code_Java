package utils_2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Utils_Day09 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        Set<Point> redTiles = new HashSet<>();

        // Parse each line to extract coordinates
        for (String line : lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            redTiles.add(new Point(x, y));
        }

        long maxArea = 0; // Variable to store the maximum area found
        List<Point> redTileList = new ArrayList<>(redTiles);

        // Try all pairs of red tiles to find the largest rectangle
        for (int i = 0; i < redTileList.size(); i++) {
            Point p1 = redTileList.get(i);
            for (int j = i + 1; j < redTileList.size(); j++) {
                Point p2 = redTileList.get(j);

                // Calculate rectangle dimensions and area
                // Formula: width = |x1 - x2| + 1, height = |y1 - y2| + 1
                long width = Math.abs((long) p1.x - (long) p2.x) + 1;
                long height = Math.abs((long) p1.y - (long) p2.y) + 1;
                long area = width * height;

                // Update maximum area if current area is larger
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        List<Point> redTiles = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            redTiles.add(new Point(x, y));
        }
        int n = redTiles.size();

        // Step 1: Collect all unique coordinates for compression
        Set<Integer> uniqueXs = new HashSet<>();
        Set<Integer> uniqueYs = new HashSet<>();

        for (Point p : redTiles) {
            uniqueXs.add(p.x); // Add red tile X coordinate
            uniqueYs.add(p.y); // Add red tile Y coordinate
            // Add adjacent coordinates to handle borders correctly
            uniqueXs.add(p.x - 1);
            uniqueXs.add(p.x + 1);
            uniqueYs.add(p.y - 1);
            uniqueYs.add(p.y + 1);
        }

        // Convert to sorted lists for coordinate compression
        List<Integer> sortedXs = new ArrayList<>(uniqueXs);
        List<Integer> sortedYs = new ArrayList<>(uniqueYs);
        Collections.sort(sortedXs);
        Collections.sort(sortedYs);

        // Create mappings from original coordinates to compressed indices
        Map<Integer, Integer> xToIndex = new HashMap<>();
        Map<Integer, Integer> yToIndex = new HashMap<>();
        for (int i = 0; i < sortedXs.size(); i++)
            xToIndex.put(sortedXs.get(i), i);
        for (int i = 0; i < sortedYs.size(); i++)
            yToIndex.put(sortedYs.get(i), i);

        // Step 2: Build the polygon (green borders) in compressed coordinates
        boolean[][] border = new boolean[sortedYs.size()][sortedXs.size()];

        for (int i = 0; i < n; i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % n); // Wrap around for last connection

            // Get compressed indices
            int x1Idx = xToIndex.get(p1.x);
            int y1Idx = yToIndex.get(p1.y);
            int x2Idx = xToIndex.get(p2.x);
            int y2Idx = yToIndex.get(p2.y);

            if (x1Idx == x2Idx) { // Vertical segment
                int yStart = Math.min(y1Idx, y2Idx);
                int yEnd = Math.max(y1Idx, y2Idx);
                for (int y = yStart; y <= yEnd; y++) {
                    border[y][x1Idx] = true;
                }
            } else { // Horizontal segment (y1Idx == y2Idx)
                int xStart = Math.min(x1Idx, x2Idx);
                int xEnd = Math.max(x1Idx, x2Idx);
                for (int x = xStart; x <= xEnd; x++) {
                    border[y1Idx][x] = true;
                }
            }
        }

        // Step 3: Flood fill to mark exterior (non-red, non-green) areas
        boolean[][] exterior = new boolean[sortedYs.size()][sortedXs.size()];
        Queue<int[]> queue = new LinkedList<>();

        // Start flood fill from all edges of the compressed grid
        for (int x = 0; x < sortedXs.size(); x++) {
            if (!border[0][x] && !exterior[0][x]) {
                queue.add(new int[] { 0, x });
                exterior[0][x] = true;
            }
            if (!border[sortedYs.size() - 1][x] && !exterior[sortedYs.size() - 1][x]) {
                queue.add(new int[] { sortedYs.size() - 1, x });
                exterior[sortedYs.size() - 1][x] = true;
            }
        }
        for (int y = 0; y < sortedYs.size(); y++) {
            if (!border[y][0] && !exterior[y][0]) {
                queue.add(new int[] { y, 0 });
                exterior[y][0] = true;
            }
            if (!border[y][sortedXs.size() - 1] && !exterior[y][sortedXs.size() - 1]) {
                queue.add(new int[] { y, sortedXs.size() - 1 });
                exterior[y][sortedXs.size() - 1] = true;
            }
        }

        // BFS flood fill
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < sortedYs.size() && nc >= 0 && nc < sortedXs.size()) {
                    if (!border[nr][nc] && !exterior[nr][nc]) {
                        exterior[nr][nc] = true;
                        queue.add(new int[] { nr, nc });
                    }
                }
            }
        }

        // Step 4: Determine allowed tiles (red or green)
        boolean[][] allowed = new boolean[sortedYs.size()][sortedXs.size()];
        for (int y = 0; y < sortedYs.size(); y++) {
            for (int x = 0; x < sortedXs.size(); x++) {
                // Allowed if it's part of the border (red/green) or interior (not exterior)
                allowed[y][x] = border[y][x] || !exterior[y][x];
            }
        }

        // Step 5: Build 2D prefix sum for O(1) rectangle validation
        int[][] prefix = new int[sortedYs.size() + 1][sortedXs.size() + 1];
        for (int y = 0; y < sortedYs.size(); y++) {
            for (int x = 0; x < sortedXs.size(); x++) {
                int val = allowed[y][x] ? 1 : 0;
                // Standard 2D prefix sum formula
                prefix[y + 1][x + 1] = prefix[y][x + 1] + prefix[y + 1][x] - prefix[y][x] + val;
            }
        }

        // Step 6: Search for the maximum valid rectangle
        long maxArea = 0;

        for (int i = 0; i < redTiles.size(); i++) {
            Point p1 = redTiles.get(i);
            for (int j = i + 1; j < redTiles.size(); j++) {
                Point p2 = redTiles.get(j);

                // Calculate rectangle boundaries in original coordinates
                int ox1 = Math.min(p1.x, p2.x);
                int ox2 = Math.max(p1.x, p2.x);
                int oy1 = Math.min(p1.y, p2.y);
                int oy2 = Math.max(p1.y, p2.y);

                // Verify p1 and p2 are actually opposite corners
                boolean opposite = false;
                if ((p1.x == ox1 && p1.y == oy1 && p2.x == ox2 && p2.y == oy2) ||
                        (p1.x == ox2 && p1.y == oy2 && p2.x == ox1 && p2.y == oy1) ||
                        (p1.x == ox1 && p1.y == oy2 && p2.x == ox2 && p2.y == oy1) ||
                        (p1.x == ox2 && p1.y == oy1 && p2.x == ox1 && p2.y == oy2)) {
                    opposite = true;
                }
                if (!opposite)
                    continue;

                // Calculate area in original coordinates
                long area = (long) (ox2 - ox1 + 1) * (oy2 - oy1 + 1);
                // Pruning: skip if area is not larger than current maximum
                if (area <= maxArea)
                    continue;

                // Check if all rectangle corners exist in our compressed coordinates
                if (!xToIndex.containsKey(ox1) || !xToIndex.containsKey(ox2) ||
                        !yToIndex.containsKey(oy1) || !yToIndex.containsKey(oy2)) {
                    continue;
                }

                // Get compressed indices
                int gx1 = xToIndex.get(ox1);
                int gx2 = xToIndex.get(ox2);
                int gy1 = yToIndex.get(oy1);
                int gy2 = yToIndex.get(oy2);

                // Ensure indices are in correct order
                if (gx1 > gx2) {
                    int temp = gx1;
                    gx1 = gx2;
                    gx2 = temp;
                }
                if (gy1 > gy2) {
                    int temp = gy1;
                    gy1 = gy2;
                    gy2 = temp;
                }

                // Use prefix sum to check if entire rectangle contains only allowed tiles
                int cellCount = (gx2 - gx1 + 1) * (gy2 - gy1 + 1);
                int allowedCount = prefix[gy2 + 1][gx2 + 1] - prefix[gy1][gx2 + 1]
                        - prefix[gy2 + 1][gx1] + prefix[gy1][gx1];

                // If all cells in rectangle are allowed, update maximum area
                if (allowedCount == cellCount) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    /**
     * Inner class representing a point (x, y) coordinate.
     * Used to store red tile locations.
     */
    private static class Point {
        int x; // X coordinate
        int y; // Y coordinate

        /**
         * Constructs a Point with given coordinates.
         * 
         * @param x the X coordinate
         * @param y the Y coordinate
         */
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Checks equality between this point and another object.
         * Two points are equal if they have the same x and y coordinates.
         * 
         * @param obj the object to compare with
         * @return true if the objects represent the same point
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        /**
         * Generates a hash code for this point.
         * 
         * @return the hash code
         */
        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        /**
         * Returns a string representation of this point.
         * 
         * @return string in format "(x,y)"
         */
        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

}
