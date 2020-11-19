package ca.ulaval.glo4003.times.assemblers;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateTimeAssembler {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  public CustomDateTime assemble(String dateTime) {
    LocalDateTime localDateTime;

    try {
      localDateTime = LocalDateTime.parse(dateTime, FORMATTER);
    } catch (DateTimeParseException exception) {
      throw new InvalidDateException();
    }

    return new CustomDateTime(localDateTime);
  }
}
