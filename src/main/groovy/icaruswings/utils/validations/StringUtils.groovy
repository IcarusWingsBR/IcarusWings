package icaruswings.utils.validations

public class StringUtils {

    public static Boolean containsOnlyNumbers(String number) {
        return !number.matches(".*\\D.*");
    }

    public static String cleanString(String str) {
        String sanitizedStr = str.replaceAll("[^0-9]", "")

        return sanitizedStr
    }
}