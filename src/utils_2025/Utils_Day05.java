package utils_2025;

import java.io.IOException;
import java.util.List;
import utils.Utils;

public class Utils_Day05 {

    public static long solvePart1(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);

        int freshCount = 0;
        for (String line : lines) {
            if (line.isBlank())
                continue;

            String[] parts = line.split(":");
            if (parts.length != 2)
                continue;

            String id = parts[0];
            String datesStr = parts[1];

            if (Utils_Day05.isFreshIngredient(id, datesStr)) {
                freshCount++;
            }
        }

        return freshCount;
    }

    public static long solvePart2(int year, int day) throws IOException {
        List<String> lines = Utils.readInput(year, day);
        long totalFreshIds = 0;

        for (String line : lines) {
            if (line.isBlank())
                continue;

            String[] parts = line.split(":");
            if (parts.length != 2)
                continue;

            String id = parts[0];
            String datesStr = parts[1];

            if (Utils_Day05.isFreshIngredient(id, datesStr)) {
                totalFreshIds += Long.parseLong(id);
            }
        }

        return totalFreshIds;
    }

    /**
     * Determines if an ingredient is fresh based on a list of dates.
     * An ingredient is considered fresh if more than half of the provided dates
     * are in the valid format (YYYY-MM-DD).
     *
     * @param id       -> the ingredient identifier (unused in current
     *                 implementation)
     * @param datesStr -> a comma-separated string of dates
     * @return -> true if more than half of the dates are valid, false otherwise
     */
    public static boolean isFreshIngredient(String id, String datesStr) {
        String[] dates = datesStr.split(",");
        int freshCount = 0;
        int total = dates.length;

        for (String date : dates) {
            date = date.trim();
            if (isValidDate(date)) {
                freshCount++;
            }
        }

        return freshCount > (total / 2);
    }

    /**
     * Validates if a date string matches the format YYYY-MM-DD.
     *
     * @param date -> the date string to validate
     * @return -> true if the date matches the format, false otherwise
     */
    private static boolean isValidDate(String date) {
        return !date.isEmpty() && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}