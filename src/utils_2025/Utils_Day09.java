package utils_2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Utils_Day09 {

    /**
     * Calculates the maximum rectangle area that can be formed from given points.
     * Points are provided as a map from y-coordinates to lists of x-coordinates.
     * The algorithm checks pairs of y-rows and finds overlapping x-coordinates.
     *
     * @param filas -> a map where key=y-coordinate, value=list of x-coordinates at
     *              that y
     * @return -> the maximum area of rectangles formed by the points,
     *         or 0 if no valid rectangle exists
     */
    public static int maxRectangleArea(Map<Integer, List<Integer>> filas) {
        List<Integer> ys = new ArrayList<>(filas.keySet());
        Collections.sort(ys);

        int maxArea = 0;

        for (int i = 0; i < ys.size(); i++) {
            int y1 = ys.get(i);
            List<Integer> xs1 = filas.get(y1);

            for (int j = i + 1; j < ys.size(); j++) {
                int y2 = ys.get(j);
                List<Integer> xs2 = filas.get(y2);

                Integer minX = null;
                Integer maxX = null;

                int p1 = 0, p2 = 0;

                while (p1 < xs1.size() && p2 < xs2.size()) {
                    int a = xs1.get(p1);
                    int b = xs2.get(p2);

                    if (a == b) {
                        if (minX == null)
                            minX = a;
                        maxX = a;
                        p1++;
                        p2++;
                    } else if (a < b) {
                        p1++;
                    } else {
                        p2++;
                    }
                }

                if (minX != null && !minX.equals(maxX)) {
                    int width = maxX - minX;
                    int height = Math.abs(y1 - y2);
                    int area = width * height;

                    if (area < maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }
}