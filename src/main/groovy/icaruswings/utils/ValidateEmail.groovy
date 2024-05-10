package icaruswings.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidateEmail {
    public static boolean isValidEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String regex = "^[\\w]+(?:\\.[\\w-]+)*@([\\w-]+\\.)+[A-Z]{2,4}"

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
