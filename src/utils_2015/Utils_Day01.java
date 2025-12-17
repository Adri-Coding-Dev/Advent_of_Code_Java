package utils_2015;

import java.io.IOException;
import java.util.List;

public class Utils_Day01 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        String line = lines.get(0);
        long count = 0;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '(') {
                    count++;
                } else if (c == ')') {
                    count--;
                }
            }
        }
        return count;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        String line = lines.get(0);
        int target = -1;
        long count = 0;
        long countPosition = 0;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (count == target) {
                    return countPosition;
                } else {
                    if (c == '(') {
                        countPosition++;
                        count++;
                    } else if (c == ')') {
                        countPosition++;
                        count--;
                    }
                }
            }
        }
        return countPosition;
    }
}
