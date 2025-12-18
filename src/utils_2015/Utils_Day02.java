package utils_2015;

import java.io.IOException;
import java.util.List;

public class Utils_Day02 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long suma = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line != null) {
                String parts[] = line.split("x");

                int h = Integer.parseInt(parts[0]);
                int w = Integer.parseInt(parts[1]);
                int l = Integer.parseInt(parts[2]);
                suma += operate(h, w, l);
            }
        }
        return suma;
    }

    private static long operate(int h, int w, int l) {
        int side1 = h * w;
        int side2 = w * l;
        int side3 = l * h;
        int smallestSide = Math.min(side1, Math.min(side2, side3));
        return (2 * side1) + (2 * side2) + (2 * side3) + smallestSide;
    }
}
