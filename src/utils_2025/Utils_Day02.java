package utils_2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils_Day02 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long totalSum = 0;

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] ranges = line.split(",");
            for (String range : ranges) {
                String[] parts = range.split("-");
                long L = Long.parseLong(parts[0]);
                long R = Long.parseLong(parts[1]);
                totalSum += Utils_Day02.sumInvalidInRange(L, R);
            }
        }

        return totalSum;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long total = 0;

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] rangesStr = line.split(",");
            List<long[]> ranges = new ArrayList<>();
            long globalMax = 0;
            for (String range : rangesStr) {
                String[] parts = range.split("-");
                long a = Long.parseLong(parts[0]);
                long b = Long.parseLong(parts[1]);
                ranges.add(new long[] { a, b });
                if (b > globalMax)
                    globalMax = b;
            }

            TreeSet<Long> invalidSet = Utils_Day02.generateInvalidNumbers(globalMax);
            List<Long> invalidList = new ArrayList<>(invalidSet);

            for (long[] range : ranges) {
                long a = range[0];
                long b = range[1];
                int startIdx = Utils_Day02.lowerBound(invalidList, a);
                for (int i = startIdx; i < invalidList.size(); i++) {
                    long num = invalidList.get(i);
                    if (num > b)
                        break;
                    total += num;
                }
            }
        }
        return total;
    }

    /**
     * Calculates the sum of all invalid numbers within the specified inclusive
     * range [L, R].
     * A number is considered invalid if all its digits are either all even or all
     * odd.
     *
     * @param L -> the left bound of the range (inclusive)
     * @param R -> the right bound of the range (inclusive)
     * @return ->the sum of all invalid numbers between L and R
     */
    public static long sumInvalidInRange(long L, long R) {
        long sum = 0;
        for (long num = L; num <= R; num++) {
            if (!tieneParYImpar(num)) {
                sum += num;
            }
        }
        return sum;
    }

    /**
     * Determines whether a number contains at least one even digit AND one odd
     * digit.
     *
     * @param num -> the number to check
     * @return -> true if the number contains both even and odd digits, false
     *         otherwise
     */
    private static boolean tieneParYImpar(long num) {
        boolean tienePar = false;
        boolean tieneImpar = false;
        String s = String.valueOf(num);
        for (char c : s.toCharArray()) {
            int digito = c - '0';
            if (digito % 2 == 0) {
                tienePar = true;
            } else {
                tieneImpar = true;
            }
            if (tienePar && tieneImpar)
                return true;
        }
        return false;
    }

    /**
     * Generates a sorted set of invalid numbers up to a specified maximum value.
     * Invalid numbers are those composed entirely of even digits or entirely of odd
     * digits.
     *
     * @param maxVal -> the maximum number to generate up to
     * @return -> a TreeSet containing all invalid numbers ≤ maxVal
     */
    public static TreeSet<Long> generateInvalidNumbers(long maxVal) {
        TreeSet<Long> invalidSet = new TreeSet<>();
        long limit = Math.max(maxVal, 10000000000L); // Up to 10 digits

        // Generate numbers with all even digits
        generarNumerosPares(0, 10, 0, invalidSet, limit);

        // Generate numbers with all odd digits
        generarNumerosImpares(0, 10, 0, invalidSet, limit);

        return invalidSet;
    }

    /**
     * Recursively generates numbers composed entirely of even digits.
     *
     * @param current   -> the current number being built
     * @param maxDigits -> maximum number of digits allowed
     * @param depth     -> current recursion depth (number of digits used)
     * @param set       -> the set to add valid numbers to
     * @param limit     -> maximum allowed number value
     */
    private static void generarNumerosPares(long current, int maxDigits, int depth, Set<Long> set, long limit) {
        if (current > limit || depth > maxDigits)
            return;
        if (current > 0 && depth > 0)
            set.add(current);

        for (int d = 0; d <= 8; d += 2) {
            generarNumerosPares(current * 10 + d, maxDigits, depth + 1, set, limit);
        }
    }

    /**
     * Recursively generates numbers composed entirely of odd digits.
     *
     * @param current   -> the current number being built
     * @param maxDigits -> maximum number of digits allowed
     * @param depth     -> current recursion depth (number of digits used)
     * @param set       -> the set to add valid numbers to
     * @param limit     -> maximum allowed number value
     */
    private static void generarNumerosImpares(long current, int maxDigits, int depth, Set<Long> set, long limit) {
        if (current > limit || depth > maxDigits)
            return;
        if (current > 0 && depth > 0)
            set.add(current);

        for (int d = 1; d <= 9; d += 2) {
            generarNumerosImpares(current * 10 + d, maxDigits, depth + 1, set, limit);
        }
    }

    /**
     * Performs binary search to find the first index where the element is ≥ target.
     * Equivalent to lower_bound in C++.
     *
     * @param list   -> the sorted list to search
     * @param target -> the target value
     * @return -> the first index where list.get(index) ≥ target, or list.size() if
     *         no
     *         such element
     */
    public static int lowerBound(List<Long> list, long target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}