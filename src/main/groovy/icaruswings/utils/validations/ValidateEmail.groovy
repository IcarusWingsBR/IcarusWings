package icaruswings.utils.validations

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidateEmail {

    public static Boolean isValidEmail(String email) {

        if(!isValidEmailLength(email)) return false

        if(!email.contains("@")) return false

        String[] emailParts = email.split("@")

        if(!isValidUsername(emailParts[0])) return false

        if(!isValidEmailDomain(emailParts[1])) return false

        return true;
    }

    public static Boolean isValidEmailLength(String email) {
        if(email.length() < 10 || email.length() > 255) return false

        return true
    }

    public static Boolean isValidUsername(String username) {
        String regex = "^[\\w]+(?:\\.[\\w-]+)*"

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        Matcher matcher = pattern.matcher(username)

        if (matcher.matches()) return true

        return false
    }

    public static Boolean isValidEmailDomain(String emailDomain) {
        String regex = "^([\\w-]+\\.)+[A-Z]{2,4}"

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        Matcher matcher = pattern.matcher(emailDomain)

        if (matcher.matches()) return true

        return false
    }
}