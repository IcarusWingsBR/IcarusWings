package icaruswings.utils.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValueValidator {

    public static Boolean isValid(String stringValue) {
        if (!isFormatValid(stringValue)) return false
        print stringValue
        String valueWithDote = stringValue.replaceAll( "," , "." )
        BigDecimal value = new BigDecimal(valueWithDote)

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