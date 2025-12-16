package utils_2025;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils_Day06 {

    public static BigInteger solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        Map<Integer, List<String>> mathMap = new HashMap<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        Pattern sigPattern = Pattern.compile("[*+]");

        for (String line : lines) {
            if (line.isBlank())
                continue;

            Matcher m = null;
            if (line.contains("*")) {
                m = sigPattern.matcher(line);
            } else {
                m = numberPattern.matcher(line);
            }

            int incrementador = 0;
            while (m.find()) {
                if (!mathMap.containsKey(incrementador)) {
                    mathMap.put(incrementador, new ArrayList<>());
                }
                mathMap.get(incrementador).add(m.group(0));
                incrementador++;
            }
        }

        BigInteger suma = BigInteger.ZERO;
        for (List<String> ent : mathMap.values()) {
            BigInteger val = new BigInteger(ent.get(0));
            for (int i = 1; i < ent.size() - 1; i++) {
                if (ent.get(ent.size() - 1).equals("+")) {
                    val = val.add(new BigInteger(ent.get(i)));
                } else if (ent.get(ent.size() - 1).equals("*")) {
                    val = val.multiply(new BigInteger(ent.get(i)));
                }
            }

            suma = suma.add(val);
        }
        return suma;
    }

    public static BigInteger solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        Map<Integer, List<String>> mathMap = new HashMap<>();
        for (String line : lines) {
            if (line.isBlank())
                continue;
            String[] arr = line.split("");
            for (int i = 0; i < arr.length; i++) {
                if (!mathMap.containsKey(i)) {
                    mathMap.put(i, new ArrayList<>());
                }
                mathMap.get(i).add(arr[i]);
            }
        }
        BigInteger suma = BigInteger.ZERO;
        BigInteger sumaTotal = BigInteger.ZERO;
        boolean multi = false;
        for (List<String> line : mathMap.values()) {
            if (line.get(line.size() - 1).equals("+")) {
                multi = false;
                suma = BigInteger.ZERO;
            } else if (line.get(line.size() - 1).equals("*")) {
                multi = true;
                suma = BigInteger.ZERO;
            }

            String valorLinea = "";
            for (int i = 0; i < line.size() - 1; i++) {
                valorLinea += line.get(i);
            }
            if (valorLinea.isBlank()) {
                sumaTotal = sumaTotal.add(suma);
                continue;
            }
            BigInteger val = new BigInteger(valorLinea.trim());
            if (multi) {
                if (suma.equals(BigInteger.ZERO)) {
                    suma = val;
                } else {
                    suma = suma.multiply(val);
                }
            } else {
                suma = suma.add(val);
            }
        }
        return sumaTotal;
    }

}
