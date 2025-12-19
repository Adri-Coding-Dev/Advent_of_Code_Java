package utils_2015;

import java.io.IOException;
import java.util.List;
import utils.Utils;

public class Utils_Day03 {
    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int x = 0, y = 0, xMin = 0, xMax = 0, yMin = 0, yMax = 0;
        String line = lines.get(0);
        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);

            if (c == 'v') {
                y--;
            } else if (c == '^') {
                y++;
            } else if (c == '<') {
                x--;
            } else if (c == '>') {
                x++;
            } else {
                System.out.println("No deberia...");
            }

            if (x > xMax) {
                xMax = x;
            }
            if (x < xMin) {
                xMin = x;
            }

            if (y > yMax) {
                yMax = y;
            }
            if (y < yMin) {
                yMin = y;
            }

        }

        boolean[][] mapa = new boolean[xMax - xMin + 1][yMax - yMin + 1];

        for (int j = 0; j < mapa.length; j++) {
            for (int c = 0; c < mapa[j].length; c++) {
                mapa[j][c] = false;
            }
        }

        x = -xMin;
        y = -yMin;

        mapa[x][y] = true;

        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);
            if (c == 'v') {
                y--;
            } else if (c == '^') {
                y++;
            } else if (c == '<') {
                x--;
            } else if (c == '>') {
                x++;
            } else {
                System.out.println("No deberia...");
            }

            mapa[x][y] = true;

        }

        int contador = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j]) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        int x = 0, y = 0, xMin = 0, xMax = 0, yMin = 0, yMax = 0, xRobot = 0, yRobot = 0;
        String line = lines.get(0);
        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);

            if (c == 'v') {
                if (j % 2 == 0)
                    y--;
                else
                    yRobot--;
            } else if (c == '^') {
                if (j % 2 == 0)
                    y++;
                else
                    yRobot++;
            } else if (c == '<') {
                if (j % 2 == 0)
                    x--;
                else
                    xRobot--;
            } else if (c == '>') {
                if (j % 2 == 0)
                    x++;
                else
                    xRobot++;
            } else {
                System.out.println("No deberia...");
            }

            if (x > xMax) {
                xMax = x;
            }
            if (x < xMin) {
                xMin = x;
            }

            if (y > yMax) {
                yMax = y;
            }
            if (y < yMin) {
                yMin = y;
            }

            if (xRobot > xMax) {
                xMax = xRobot;
            }
            if (xRobot < xMin) {
                xMin = xRobot;
            }

            if (yRobot > yMax) {
                yMax = yRobot;
            }
            if (yRobot < yMin) {
                yMin = yRobot;
            }

        }

        boolean[][] mapa = new boolean[(xMax - xMin + 1) * 2][(yMax - yMin + 1) * 2];

        for (int j = 0; j < mapa.length; j++) {
            for (int c = 0; c < mapa[j].length; c++) {
                mapa[j][c] = false;
            }
        }

        x = -xMin;
        y = -yMin;
        xRobot = x;
        yRobot = y;

        mapa[x][y] = true;

        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);
            if (c == 'v') {
                if (j % 2 == 0)
                    y--;
                else
                    yRobot--;
            } else if (c == '^') {
                if (j % 2 == 0)
                    y++;
                else
                    yRobot++;
            } else if (c == '<') {
                if (j % 2 == 0)
                    x--;
                else
                    xRobot--;
            } else if (c == '>') {
                if (j % 2 == 0)
                    x++;
                else
                    xRobot++;
            } else {
                System.out.println("No deberia...");
            }

            mapa[x][y] = true;
            mapa[xRobot][yRobot] = true;

        }

        int contador = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j]) {
                    contador++;
                }
            }
        }
        return contador;
    }
}
