package icaruswings.utils.validations

import java.util.regex.Matcher
import java.util.regex.Pattern

public class StringUtils {

    public static Boolean containsOnlyNumbers(String number) {
        return !number.matches(".*\\D.*");
    }

    public static Boolean isValidString(String name) {
        if(!isValidStringLength(name)) return false

        if(!noNumberAndSpace(name)) return false

        return true;
    }

    private static Boolean isValidStringLength(String name) {
        if(name.length() < 3 || name.length() > 255) return false

        return true
    }

    private static Boolean noNumberAndSpace(String name) {
        String regex = "(?!^\\s)[[ ]|\\p{L}*]+";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) return false

        return true
    }
}