package icaruswings.utils.validations

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateName {

    public static Boolean isValidName(String name) {
        if(!isValidNameLength(name)) return false

        if(!isValidNameFormat(name)) return false

        return true;
    }

    public static Boolean isValidNameFormat(String name) {
        String regex = "(?!^\\s)[[ ]|\\p{L}*]+";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) return false

        return true
    }

    public static Boolean isValidNameLength(String name) {
        if(name.length() < 3 || name.length() > 255) return false

        return true
    }
}