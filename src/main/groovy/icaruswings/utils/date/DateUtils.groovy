package icaruswings.utils.date

import java.text.SimpleDateFormat
import java.text.ParseException
    
public class DateUtils {
    
    public static Boolean isBeforeToday(Date date) {
        Date receivedDate = new Date(date.getTime())
        Date today = removeTime(new Date())

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

        public static Date removeTime(Date date) {
        try {
            Calendar calendar = Calendar.getInstance()
            calendar.setTime(date)

            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            return calendar.getTime()
        } catch (ParseException parseException) {
            throw new ParseException("Formato de data inválido: " + parseException);
        }
    }
}