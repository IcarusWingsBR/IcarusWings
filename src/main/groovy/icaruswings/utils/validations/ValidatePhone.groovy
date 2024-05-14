package icaruswings.utils.validations

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidatePhone {
    
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+[1-9]\\d{1,14}"
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
