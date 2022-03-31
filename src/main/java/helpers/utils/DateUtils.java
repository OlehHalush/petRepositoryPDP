package helpers.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public final static String SURVEY_DETAILS_DATE_FORMAT = "M/d/yyyy";
    public final static String DATE_OF_VISIT_DATE_FORMAT = "MMM d, yyyy";
    public final static String NATIVE_IOS_CALENDAR_DATE_FORMAT = "EEEE, MMMM d";

    private static Locale getCurrentLocale() {
        return Locale.US;
    }

    private static ZoneId getDefaultZoneId() {
        return ZoneId.of("America/New_York");
    }

    public static String getDefaultStringLocalDateNow() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String getDefaultStringLocalDateSurveyStartDate() {
        return LocalDate.now().withDayOfMonth(1).minusDays(1).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String getDefaultStringLocalDateSurveyLastDate() {
        return LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).plusDays(1).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String getDefaultStringLocalDateNowPlusDays(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String convertDefaultDateToPattern(String defaultDate, String toPattern) {
        LocalDate localDate = LocalDate.parse(defaultDate);
        return localDate.format(DateTimeFormatter.ofPattern(toPattern, getCurrentLocale()));
    }

    public static GregorianCalendar convertDefaultDateToGregorianCalendar(String defaultDate) {
        LocalDate localDate = LocalDate.parse(defaultDate);
        return GregorianCalendar.from(localDate.atStartOfDay(getDefaultZoneId()));
    }

    public static String convertDateStringByPatternToDefaultDateFormat(String date, String datePattern) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
        return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String getDateForActivityDateOfVisit(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_OF_VISIT_DATE_FORMAT, getCurrentLocale()));
    }

    public static String convertAPIDateToPattern(String defaultDate, String toPattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(defaultDate);
        return localDateTime.format(DateTimeFormatter.ofPattern(toPattern, getCurrentLocale()));
    }
}
