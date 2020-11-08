package ca.ulaval.glo4003.times.helpers;

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

  public int endYear = defaultDateTimeEnd.getYear();

  public static TimePeriodBuilder aTimePeriod() {
    return new TimePeriodBuilder();
  }

  public TimePeriodBuilder withYears(int startYear, int endYear) {
    this.startYear = startYear;
    this.endYear = endYear;
    return this;
  }

  public TimePeriod build() {
    CustomDateTime start = aDateTime().withYear(startYear).build();
    CustomDateTime end = aDateTime().withYear(endYear).build();
    return start.isBefore(end) ? new TimePeriod(start, end) : new TimePeriod(end, start);
  }
}
