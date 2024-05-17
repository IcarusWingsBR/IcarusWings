package icaruswings.utils.validations

import java.util.regex.Matcher
import java.util.regex.Pattern

public class StringUtils {

    public static Boolean containsOnlyNumbers(String number) {
        return !number.matches(".*\\D.*");
    }

    public static Boolean isValidString(String str) {
        if(!isValidStringLength(str)) return false

        if(!noNumberAndSpace(str)) return false

        return true;
    }

    private static Boolean isValidStringLength(String str) {
        if(str.length() < 3 || str.length() > 255) return false

        return true
    }

    private static Boolean noNumberAndSpace(String str) {
        String regex = "(?!^\\s)[[ ]|\\p{L}*]+";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) return false

        return true
    }
}