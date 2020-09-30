package ca.ulaval.glo4003.times.assemblers;

import ca.ulaval.glo4003.times.domain.TimeOfDay;
import ca.ulaval.glo4003.times.exceptions.InvalidTimeOfDayException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeOfDayAssembler {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

  public TimeOfDay assemble(String time) {
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
