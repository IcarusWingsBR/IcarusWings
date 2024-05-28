package icaruswings.utils.date

import java.text.SimpleDateFormat
import java.text.ParseException
    
public class DateUtils {
    
    public static Boolean isBeforeToday(String date) {
        Date parsedDate = parse(date)
        
        return isBeforeToday(parsedDate)
    }

    public static Boolean isBeforeToday(Date date) {
        Date receivedDate = new Date(date.getTime())
        Date today = new Date(System.currentTimeMillis());
        
        if (receivedDate.before(today)) return true

        return false
    }

    public static Date parse(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
            Date parsedDate = simpleDateFormat.parse(dateString)

            return parsedDate
        } catch (ParseException parseException) {
            throw new ParseException("Formato de data inválido: " + parseException)
        } 
    }
}