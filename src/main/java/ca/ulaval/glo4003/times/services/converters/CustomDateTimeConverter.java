package ca.ulaval.glo4003.times.services.converters;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.exceptions.InvalidDateTimeException;
import ca.ulaval.glo4003.times.services.dto.DateTimeDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateTimeConverter {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  public CustomDateTime convert(DateTimeDto dateTimeDto) {
    if (dateTimeDto.dateTime == null) throw new InvalidDateTimeException();

    LocalDateTime localDateTime;

    try {
      localDateTime = LocalDateTime.parse(dateTimeDto.dateTime, FORMATTER);
    } catch (DateTimeParseException exception) {
      throw new InvalidDateTimeException();
    }

    return new CustomDateTime(localDateTime);
  }
}
