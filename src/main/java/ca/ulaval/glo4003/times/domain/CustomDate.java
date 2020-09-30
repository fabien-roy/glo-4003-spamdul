package ca.ulaval.glo4003.times.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {
  private final LocalDate localDate;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public CustomDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public boolean isFuture() {
    return LocalDate.now().isBefore(localDate);
  }

  @Override
  public String toString() {
    return localDate.format(formatter);
  }

  public LocalDate toLocalDate() {
    return localDate;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    CustomDate customDate = (CustomDate) object;

    return localDate.equals(customDate.toLocalDate());
  }

  @Override
  public int hashCode() {
    return localDate.hashCode();
  }
}
