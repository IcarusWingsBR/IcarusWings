package icaruswings.utils.parsers

class BigDecimalParser {

    public static BigDecimal parse(String stringValue) {
        String valueWithDote = stringValue.replaceAll( "," , "." )
        BigDecimal value = new BigDecimal(valueWithDote)

        return value
    }
}