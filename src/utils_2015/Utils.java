package utils_2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    // ==========|| Generals Methods ||==========//

    /**
     * read the inputs and save in a String ArrayList
     * 
     * @param year -> Number year of calendar
     * @param day  -> Day number
     * @return -> a String ArrayList
     * @throws IOException -> If the path dont match
     */
    public static List<String> readInput(int year, int day) throws IOException {
        String filePath = String.format("src/inputs_%d/Input_Day%02d.txt", year, day);
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }

    /**
     * read the inputs and save in a Integer and Integer list Map
     * 
     * @param year -> Number year of calendar
     * @param day  -> Day number
     * @return -> a Map Integer
     * @throws IOException -> If the path dont match
     */
    public static Map<Integer, List<Integer>> readInputMap(int year, int day) throws IOException {
        List<String> lines = readInput(year, day);

        Map<Integer, List<Integer>> filas = new HashMap<>();

        for (String line : lines) {
            String[] partes = line.split(",");
            int x = Integer.parseInt(partes[0].trim());
            int y = Integer.parseInt(partes[1].trim());

            filas.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        for (List<Integer> xs : filas.values()) {
            Collections.sort(xs);
        }

        return filas;
    }
}