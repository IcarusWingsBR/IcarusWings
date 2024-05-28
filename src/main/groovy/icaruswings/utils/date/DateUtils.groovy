package icaruswings.utils.date

import java.sql.Date
import java.text.SimpleDateFormat
import java.text.ParseException
    
public class DateUtils {
    
    public static Boolean isBeforeToday(String data) {
        Date parsedData = parse(data)
        
        return isBeforeToday(parsedData)
    }

    public static Boolean isBeforeToday(Date date) {
        Date receivedDate = new Date(date.getTime())
        Date today = new Date(System.currentTimeMillis());
        
        if (receivedDate.before(today)) return false

        return true
    }

    public static Date parse(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")

            return new Date(simpleDateFormat.parse(dateString).getTime())
        } catch (ParseException parseException) {
            throw new ParseException("Formato de data inválido: " + parseException)
        } 
    }
}