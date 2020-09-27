package ca.ulaval.glo4003.domain.time;

import ca.ulaval.glo4003.domain.time.exception.InvalidDateException;
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

  // TODO : Should CustomDateAssembler convert date to string?
}
