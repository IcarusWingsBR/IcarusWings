package icaruswings

class FormatTagLib {
    static namespace = "formatTagLib"

    def formatedDate = { attrs, body ->
        Date date = attrs.date

        if (date) {
            out << g.formatDate(format: "dd/MM/yyyy", date: date)
        }
    }

    String formatValue = { attrs, body ->
        BigDecimal value = attrs.value

        if (value) {
            String formattedValue = String.format("%,.2f", value)
            out << formattedValue
        }
    }
}