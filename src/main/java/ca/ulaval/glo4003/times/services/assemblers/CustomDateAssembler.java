package ca.ulaval.glo4003.times.services.assemblers;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateAssembler {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public CustomDate assemble(String date) {
    LocalDate localDate;

    try {
      localDate = LocalDate.parse(date, FORMATTER);
    } catch (DateTimeParseException exception) {
      throw new InvalidDateException();
    }

    return new CustomDate(localDate);
  }
}
