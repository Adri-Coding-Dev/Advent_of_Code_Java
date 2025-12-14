package utils_2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    // ==========|| Metodos Generales ||==========//
    public static List<String> readInput(int year, int day) throws IOException {
        String filePath = String.format("src/inputs_%d/Input_Day%02d.txt", year, day);
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }
}