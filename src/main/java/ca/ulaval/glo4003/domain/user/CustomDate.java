package ca.ulaval.glo4003.domain.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {
  private LocalDate localDate;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

  public CustomDate(String localDate) {
    this.localDate = LocalDate.parse(localDate, formatter);
  }

  @Override
  public String toString() {
    return this.localDate.format(formatter);
  }
}
