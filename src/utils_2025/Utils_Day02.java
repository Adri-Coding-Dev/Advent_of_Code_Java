package utils_2025;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils_Day02 {

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

    // Suma de números inválidos en un rango [L, R]
    public static long sumInvalidInRange(long L, long R) {
        long sum = 0;
        for (long num = L; num <= R; num++) {
            if (!tieneParYImpar(num)) {
                sum += num;
            }
        }
        return sum;
    }

    // Genera números inválidos hasta maxVal
    public static TreeSet<Long> generateInvalidNumbers(long maxVal) {
        TreeSet<Long> invalidSet = new TreeSet<>();
        long limit = Math.max(maxVal, 10000000000L); // Hasta 10 dígitos

        // Generar números con todos dígitos pares
        generarNumerosPares(0, 10, 0, invalidSet, limit);

        // Generar números con todos dígitos impares
        generarNumerosImpares(0, 10, 0, invalidSet, limit);

        return invalidSet;
    }

    private static void generarNumerosPares(long current, int maxDigits, int depth, Set<Long> set, long limit) {
        if (current > limit || depth > maxDigits)
            return;
        if (current > 0 && depth > 0)
            set.add(current);

        for (int d = 0; d <= 8; d += 2) {
            generarNumerosPares(current * 10 + d, maxDigits, depth + 1, set, limit);
        }
    }

    private static void generarNumerosImpares(long current, int maxDigits, int depth, Set<Long> set, long limit) {
        if (current > limit || depth > maxDigits)
            return;
        if (current > 0 && depth > 0)
            set.add(current);

        for (int d = 1; d <= 9; d += 2) {
            generarNumerosImpares(current * 10 + d, maxDigits, depth + 1, set, limit);
        }
    }

    // Encuentra el índice del primer elemento >= target (búsqueda binaria)
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
