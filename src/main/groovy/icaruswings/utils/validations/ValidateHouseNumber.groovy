package icaruswings.utils.validations

public class ValidateHouseNumber {

    public static Boolean isValidHouseNumber(String number) {
        return !number.matches(".*\\D.*");
    }
}