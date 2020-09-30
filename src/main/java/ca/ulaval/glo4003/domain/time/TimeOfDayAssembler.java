package ca.ulaval.glo4003.domain.time;

import ca.ulaval.glo4003.domain.time.exception.InvalidTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeOfDayAssembler {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

  public TimeOfDay assemble(String date) {
    LocalTime localTime;

    try {
      localTime = LocalTime.parse(date, FORMATTER);
    } catch (DateTimeParseException exception) {
      throw new InvalidTimeException();
    }

    return new TimeOfDay(localTime);
  }
}
