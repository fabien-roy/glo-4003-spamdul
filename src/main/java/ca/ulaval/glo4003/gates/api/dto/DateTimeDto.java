package ca.ulaval.glo4003.gates.api.dto;

public class DateTimeDto {
  public String dateTime;

  @Override
  public String toString() {
    return String.format("DateTimeDto{dayOfWeek='%s'}", dateTime);
  }
}
