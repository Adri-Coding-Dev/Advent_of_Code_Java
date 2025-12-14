package utils_2025;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Utils_Day03 {

    /**
     * Extracts digits from a string and calculates the maximum product of any two
     * digits.
     * Uses brute force to check all combinations of two digits.
     *
     * @param line -> the input string containing digits
     * @return -> the maximum product of any two digits found in the string
     */
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

    /**
     * Extracts digits from a string and calculates the product of the K largest
     * digits.
     * Uses a max-heap (priority queue) to efficiently select the top K digits.
     *
     * @param line -> the input string containing digits
     * @param K    -> the number of largest digits to multiply
     * @return -> the product of the K largest digits, or 1 if K=0
     */
    public static int getMaxJoltage(String line, int K) {
        List<Integer> batteries = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                batteries.add(Character.getNumericValue(c));
            }
        }

        // Select the K batteries with the highest voltage
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