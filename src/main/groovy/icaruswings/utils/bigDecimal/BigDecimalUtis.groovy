package icaruswings.utils.bigDecimal

import java.util.regex.Matcher
import java.util.regex.Pattern

class BigDecimalUtis {

    public static BigDecimal parse(String stringValue) {
        String valueWithDote = stringValue.replaceAll( "," , "." )
        BigDecimal value = new BigDecimal(valueWithDote)

        return value
    }
}