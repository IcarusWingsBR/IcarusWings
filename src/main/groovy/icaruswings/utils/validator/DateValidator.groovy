package icaruswings.utils.validator

import java.time.LocalDate;
import java.sql.Date
import icaruswings.utils.date.DateParser
    
public class DateValidator {
    
    public static Boolean isValid(String data) {
        Date parsedData = DateParser.parse(data)
        
        return isValid(parsedData)
    }

    public static Boolean isValid(Date date) {
        LocalDate localDate = convertToLocalDate(date);
        LocalDate today = LocalDate.now();
        
        if (localDate.isBefore(today)) return false

        return true
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toLocalDate()
    }
}