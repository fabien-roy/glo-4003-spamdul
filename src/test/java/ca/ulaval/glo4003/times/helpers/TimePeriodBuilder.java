package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.times.helpers.CalendarHelper.*;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createLocalDateTime;
import static ca.ulaval.glo4003.times.helpers.TimePeriodMother.createAmountOfDays;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.time.LocalDateTime;

public class TimePeriodBuilder {

  private final LocalDateTime defaultDateTimeStart = createLocalDateTime();
  private final LocalDateTime defaultDateTimeEnd =
      defaultDateTimeStart.plusDays(createAmountOfDays());

  public int startYear = defaultDateTimeStart.getYear();
  public int startMonth = defaultDateTimeStart.getMonthValue();
  public int startDayOfMonth = defaultDateTimeStart.getDayOfMonth();

  public int endYear = defaultDateTimeEnd.getYear();
  public int endMonth = defaultDateTimeEnd.getMonthValue();
  public int endDayOfMonth = defaultDateTimeEnd.getDayOfMonth();

  public static TimePeriodBuilder aTimePeriod() {
    return new TimePeriodBuilder();
  }

  public TimePeriodBuilder withYears(int startYear, int endYear) {
    this.startYear = startYear;
    this.endYear = endYear;
    return this;
  }

  public TimePeriodBuilder withMonths(int startMonth, int endMonth) {
    this.startMonth = startMonth;
    this.endMonth = endMonth;
    this.startDayOfMonth = firstDayOfMonth(endYear, toJavaCalendarMonth(startMonth));
    this.endDayOfMonth = lastDayOfMonth(endYear, toJavaCalendarMonth(endMonth));
    return this;
  }

  public TimePeriod build() {
    CustomDateTime start =
        aDateTime()
            .withYear(startYear)
            .withMonth(startMonth)
            .withDayOfMonth(startDayOfMonth)
            .build();
    CustomDateTime end =
        aDateTime().withYear(endYear).withMonth(endMonth).withDayOfMonth(endDayOfMonth).build();
    return start.isBefore(end) ? new TimePeriod(start, end) : new TimePeriod(end, start);
  }
}
