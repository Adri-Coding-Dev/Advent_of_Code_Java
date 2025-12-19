package utils_2015;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Utils;

public class Utils_Day05 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int contador = 0;
        for (String line : lines) {
            int contadorVocales = 0;
            boolean dosLetrasIgualesSeguidas = false;
            boolean noTieneLetrasSeguidas = true;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'a' || line.charAt(i) == 'e' || line.charAt(i) == 'i' || line.charAt(i) == 'o'
                        || line.charAt(i) == 'u')
                    contadorVocales++;

                if (i > 0) {
                    if (line.charAt(i) == line.charAt(i - 1)) {
                        dosLetrasIgualesSeguidas = true;
                    }
                    if (line.charAt(i) - 1 == line.charAt(i - 1)) {
                        if (line.charAt(i) == 'b' || line.charAt(i) == 'd' || line.charAt(i) == 'q'
                                || line.charAt(i) == 'y') {
                            noTieneLetrasSeguidas = false;
                        }
                    }
                }
            }
            if (contadorVocales > 2 && dosLetrasIgualesSeguidas && noTieneLetrasSeguidas) {
                contador++;
            }
        }
        return contador;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int contador = 0;

        for (String line : lines) {
            boolean reglaA = false;
            boolean reglaB = false;

            // Regla B: letra que se repite con una en medio (xyx)
            for (int i = 0; i < line.length() - 2; i++) {
                if (line.charAt(i) == line.charAt(i + 2)) {
                    reglaB = true;
                    break;
                }
            }

            // Regla A: par repetido sin solaparse
            Map<String, Integer> posiciones = new HashMap<>();

            for (int i = 0; i < line.length() - 1; i++) {
                String par = line.substring(i, i + 2);

                if (posiciones.containsKey(par)) {
                    int anterior = posiciones.get(par);
                    if (i - anterior >= 2) {
                        reglaA = true;
                        break;
                    }
                } else {
                    posiciones.put(par, i);
                }
            }

            if (reglaA && reglaB) {
                contador++;
            }
        }

        return contador;
    }

}
