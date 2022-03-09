package es.sequra.disbursementwebapplication.utils;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class Utils {

    /**
     * This method returns the number of YEAR from a LocalDate object
     * @param localDate a LocalDate object, cannot be null
     * @return number that represents the week on the year (1 - 52 or 53, depends on year)
     * returns 0 if LocalDate is an invalid object
     */
    public static int getWeekOfYearFromLocalDate(LocalDate localDate) {
        try {
            return localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        } catch (Exception exception) {
            return 0;
        }

    }


    /**
     * This method returns the number of YEAR from a LocalDate object
     * @param localDate a LocalDate object, cannot be null
     * @return the year value in LocalDate object e.g. 2010, 2011, 2012 ...
     * returns 0 if LocalDate is an invalid object
     */
    public static int getWeekYearFromLocalDate(LocalDate localDate) {
        try {
            return localDate.get(IsoFields.WEEK_BASED_YEAR);
        } catch (Exception exception) {
            return 0;
        }
    }
}
