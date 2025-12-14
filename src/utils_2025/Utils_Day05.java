package utils_2025;

public class Utils_Day05 {

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