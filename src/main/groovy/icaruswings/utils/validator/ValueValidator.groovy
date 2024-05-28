package icaruswings.utils.validator

import java.util.regex.Matcher
import java.util.regex.Pattern
import icaruswings.utils.parsers.BigDecimalParser

class ValueValidator {

    public static Boolean isValid(String stringValue) {
        if (!isFormatValid(stringValue)) return false

        BigDecimal value = BigDecimalParser.parse(stringValue)

        if (value < 0) return false

        return true
    }

    private static Boolean isFormatValid(String stringValue) {
        String regex = "^\\d+(,\\d+)?(?<!,)"

        Pattern pattern = Pattern.compile(regex)
        Matcher matcher = pattern.matcher(stringValue)

        if (!matcher.matches()) return false

        return true
    }
}