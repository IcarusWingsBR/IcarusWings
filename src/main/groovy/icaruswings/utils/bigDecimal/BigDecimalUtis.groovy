package icaruswings.utils.bigDecimal

import java.util.regex.Matcher
import java.util.regex.Pattern

class BigDecimalUtis {

    public static Boolean isValidBigDecimal(String stringValue) {
        if (!isFormatValid(stringValue)) return false

        BigDecimal value = parse(stringValue)

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

    public static BigDecimal parse(String stringValue) {
        String valueWithDote = stringValue.replaceAll( "," , "." )
        BigDecimal value = new BigDecimal(valueWithDote)

        return value
    }
}