package ca.ulaval.glo4003.times.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimePeriod {
  private final CustomDateTime start;
  private final CustomDateTime end;

  public TimePeriod(CustomDateTime start, CustomDateTime end) {
    this.start = start;
    this.end = end;
  }

  public CustomDateTime getStart() {
    return start;
  }

  public CustomDateTime getEnd() {
    return end;
  }

  public boolean contains(CustomDateTime dateTime) {
    // TODO : #266
    return false;
  }

  public List<TimeCalendar> getYears() {
    return getCalendars(CustomDateTime::getYear);
  }

  public List<TimeCalendar> getMonths() {
    // TODO : #266
    return Collections.emptyList();
  }

  public List<TimeCalendar> getDays() {
    // TODO : #266
    return Collections.emptyList();
  }

  private List<CustomDateTime> getDateTimes() {
    List<CustomDateTime> dateTimes = new ArrayList<>();

    CustomDateTime dateTime = start;
    do {
      dateTimes.add(dateTime);
      dateTime = dateTime.plusDays(1);
    } while (dateTime.isBefore(end));
    dateTimes.add(end);

    return dateTimes;
  }

  private List<TimeCalendar> getCalendars(GetCalendarOperator getCalendar) {
    List<TimeCalendar> calendars = new ArrayList<>();
    getDateTimes()
        .forEach(
            dateTime -> {
              if (!calendars.contains(getCalendar.operation(dateTime)))
                calendars.add(getCalendar.operation(dateTime));
            });
    Collections.sort(calendars);
    return calendars;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimePeriod period = (TimePeriod) object;

    return start.equals(period.getStart()) && end.equals(period.getEnd());
  }

  @Override
  public int hashCode() {
    return start.hashCode() + end.hashCode();
  }
}
