package utils_2025;

public class Utils_Day05 {

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

    private static boolean isValidDate(String date) {
        return !date.isEmpty() && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
