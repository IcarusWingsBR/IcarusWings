package icaruswings.utils.validations

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateName {

    public static boolean isValidName(String name) {
        boolean isNameValid = false;

        if (name != null && name.length() > 0) {
            String regex = "^[a-zA-Z]+";

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(name);

            if (matcher.matches()) {
                isNameValid = true;
            }
        }

        return isNameValid;
    }
}