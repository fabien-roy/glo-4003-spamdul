package ca.ulaval.glo4003.times.helpers;

import com.github.javafaker.Faker;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarHelper {
  public static int validDayOfMonth(int year, int month) {
    return Faker.instance()
        .random()
        .nextInt(firstDayOfMonth(year, month), lastDayOfMonth(year, month));
  }

  public static int firstDayOfMonth(int year, int month) {
    Calendar calendar = thatDayOfMonth(year, month);
    return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
  }

  public static int lastDayOfMonth(int year, int month) {
    Calendar calendar = thatDayOfMonth(year, month);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  private static Calendar thatDayOfMonth(int year, int month) {
    return new GregorianCalendar(year, month, 1);
  }

  public static int toJavaCalendarMonth(int month) {
    return month - 1;
  }

  public static LocalDateTime dateTimeAtMinimumTime(int year, int month, int dayOfMonth) {
    return LocalDateTime.of(year, month, dayOfMonth, 0, 0, 0);
  }

  public static LocalDateTime dateTimeAtMaximumTime(int year, int month, int dayOfMonth) {
    return LocalDateTime.of(year, month, dayOfMonth, 23, 59, 59, 999000000);
  }
}
