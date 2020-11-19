package ca.ulaval.glo4003.gates.api.dto;

public class DayOfWeekDto {
  public String dayOfWeek;

  @Override
  public String toString() {
    return String.format("DayOfWeekDto{dayOfWeek='%s'}", dayOfWeek);
  }
}
