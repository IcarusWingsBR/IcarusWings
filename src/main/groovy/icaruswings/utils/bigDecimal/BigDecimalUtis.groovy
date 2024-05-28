package icaruswings.utils.bigDecimal

class BigDecimalUtis {

    public static BigDecimal parse(String stringValue) {
        String valueWithDote = stringValue.replaceAll( "," , "." )
        BigDecimal value = new BigDecimal(valueWithDote)

        return value
    }
}