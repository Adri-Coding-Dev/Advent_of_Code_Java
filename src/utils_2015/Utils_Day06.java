package utils_2015;

import java.io.IOException;
import java.util.List;

import utils.Utils;

public class Utils_Day06 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        boolean[][] mapa = new boolean[1000][1000];

        for (String line : lines) {
            String action;
            String coords;

            if (line.startsWith("turn on")) {
                action = "ON";
                coords = line.substring(8);
            } else if (line.startsWith("turn off")) {
                action = "OFF";
                coords = line.substring(9);
            } else {
                action = "TOGGLE";
                coords = line.substring(7);
            }

            String[] parts = coords.split(" through ");
            String[] start = parts[0].split(",");
            String[] end = parts[1].split(",");

            int x1 = Integer.parseInt(start[0]);
            int y1 = Integer.parseInt(start[1]);
            int x2 = Integer.parseInt(end[0]);
            int y2 = Integer.parseInt(end[1]);

            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    if (action.equals("ON"))
                        mapa[x][y] = true;
                    else if (action.equals("OFF"))
                        mapa[x][y] = false;
                    else
                        mapa[x][y] = !mapa[x][y];
                }
            }
        }

        long contador = 0;
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if (mapa[x][y])
                    contador++;
            }
        }

        return contador;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int[][] mapa = new int[1000][1000];
        long contadorLuces = 0;
        for (String line : lines) {
            String action;
            String coords;

            if (line.startsWith("turn on")) {
                action = "ON";
                coords = line.substring(8);
            } else if (line.startsWith("turn off")) {
                action = "OFF";
                coords = line.substring(9);
            } else {
                action = "TOGGLE";
                coords = line.substring(7);
            }

            String[] parts = coords.split(" through ");
            String[] start = parts[0].split(",");
            String[] end = parts[1].split(",");

            int x1 = Integer.parseInt(start[0]);
            int y1 = Integer.parseInt(start[1]);
            int x2 = Integer.parseInt(end[0]);
            int y2 = Integer.parseInt(end[1]);
            ejecutarInstruccion(x1, x2, y1, y2, action, mapa);
        }
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                contadorLuces += mapa[i][j];
            }
        }

        return contadorLuces;
    }

    private static void ejecutarInstruccion(int x1, int x2, int y1, int y2, String action, int[][] mapa) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                if (action.equals("ON")) {
                    mapa[i][j]++;
                } else if (action.equals("OFF")) {
                    if (mapa[i][j] > 0)
                        mapa[i][j]--;
                } else if (action.equals("TOGGLE")) {
                    mapa[i][j] += 2;
                }
            }
        }
    }
}
