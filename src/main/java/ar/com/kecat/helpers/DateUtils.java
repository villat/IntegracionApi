package ar.com.kecat.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date getDateOneMonthLaterByDate(Date date){
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDateOneMonthLaterByDay(int day){
        return Date.from(getLocalDateOneMonthLaterByDay(day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDateOneMonthAnd14DaysLaterByDay(int day){
        return Date.from(getLocalDateOneMonthLaterByDay(day).plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDate getLocalDateOneMonthLaterByDay(int day){
        return LocalDate.now().plusMonths(1).withDayOfMonth(day);
    }
}
