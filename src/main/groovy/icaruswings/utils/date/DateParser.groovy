package icaruswings.utils.date

import java.sql.Date
import java.text.SimpleDateFormat
import java.text.ParseException

class DateParser {
    public static Date parse(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")

            return new Date(simpleDateFormat.parse(dateString).getTime())
        } catch (ParseException parseException) {
            throw new ParseException("Formato de data inválido: " + parseException)
        } 
    }
}