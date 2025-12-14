package utils_2025;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Utils_Day03 {
    public static int getMaxJoltage(String line) {
        List<Integer> batteries = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                batteries.add(Character.getNumericValue(c));
            }
        }

        int maxVoltage = 0;
        int n = batteries.size();
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) == 2) {
                int voltage = 1;
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        voltage *= batteries.get(i);
                    }
                }
                maxVoltage = Math.max(maxVoltage, voltage);
            }
        }
        return maxVoltage;
    }

    // Obtiene el máximo voltaje para K baterías
    public static int getMaxJoltage(String line, int K) {
        List<Integer> batteries = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                batteries.add(Character.getNumericValue(c));
            }
        }

        // Seleccionar las K baterías con mayor voltaje
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int volt : batteries) {
            pq.offer(volt);
        }

        int result = 1;
        for (int i = 0; i < K && !pq.isEmpty(); i++) {
            result *= pq.poll();
        }
        return result;
    }
}
