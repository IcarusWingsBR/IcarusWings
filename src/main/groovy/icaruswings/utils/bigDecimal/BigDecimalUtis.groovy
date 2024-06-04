package icaruswings.utils.bigDecimal

class BigDecimalUtis {

    public static BigDecimal parse(String stringValue) {
        String valueWithoutDots = stringValue.replace( "." , "" )
        String formattedValue = valueWithoutDots.replace( "," , "." )
        BigDecimal value = new BigDecimal(formattedValue)

        return value
    }
}