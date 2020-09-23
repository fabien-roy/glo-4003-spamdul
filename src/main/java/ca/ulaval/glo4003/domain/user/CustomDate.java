package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {
  private LocalDate localDate;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public CustomDate(String localDate) {
    try {
      this.localDate = LocalDate.parse(localDate, formatter);
    } catch (Exception e) {
      throw new InvalidBirthDateException();
    }
  }

  @Override
  public String toString() {
    return this.localDate.format(formatter);
  }
}
