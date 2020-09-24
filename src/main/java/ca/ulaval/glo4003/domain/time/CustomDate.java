package ca.ulaval.glo4003.domain.time;

import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate {
  private final LocalDate localDate;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public CustomDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public CustomDate(String localDate) {
    try {
      this.localDate = LocalDate.parse(localDate, formatter);
    } catch (Exception e) {
      throw new InvalidBirthDateException();
    }
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

  @Override
  public String toString() {
    return localDate.format(formatter);
  }

  public LocalDate toLocalDate() {
    return localDate;
  }
}
