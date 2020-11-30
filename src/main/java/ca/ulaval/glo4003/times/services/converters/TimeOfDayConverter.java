package ca.ulaval.glo4003.times.services.converters;

import ca.ulaval.glo4003.times.domain.TimeOfDay;
import ca.ulaval.glo4003.times.exceptions.InvalidTimeOfDayException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeOfDayConverter {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

  public TimeOfDay convert(String time) {
    if (time == null) throw new InvalidTimeOfDayException();

    LocalTime localTime;

    try {
      localTime = LocalTime.parse(time, FORMATTER);
    } catch (DateTimeParseException exception) {
      throw new InvalidTimeOfDayException();
    }

    return new TimeOfDay(localTime);
  }
}
