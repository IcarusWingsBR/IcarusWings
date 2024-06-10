package icaruswings.utils.string

import java.util.regex.Matcher
import java.util.regex.Pattern

public class StringUtils {

    public static Boolean containsOnlyNumbers(String number) {
        return !number.matches(".*\\D.*")
    }

    private static Boolean dontHaveNumber(String str) {
        String regex = "^[^0-9]*"

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        Matcher matcher = pattern.matcher(str)

        if (!matcher.matches()) return false

        return true
    }
}