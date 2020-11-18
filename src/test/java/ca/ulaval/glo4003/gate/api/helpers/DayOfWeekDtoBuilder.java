package ca.ulaval.glo4003.gate.api.helpers;

import static ca.ulaval.glo4003.times.helpers.DayOfWeekMother.createDayOfWeek;

import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;

public class DayOfWeekDtoBuilder {
  private String dayOfWeek = createDayOfWeek().toString();

  private DayOfWeekDtoBuilder() {}

  public static DayOfWeekDtoBuilder aDayOfWeekDto() {
    return new DayOfWeekDtoBuilder();
  }

  public DayOfWeekDtoBuilder withDayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  public DayOfWeekDto build() {
    DayOfWeekDto dayOfWeekDto = new DayOfWeekDto();
    dayOfWeekDto.dayOfWeek = dayOfWeek;
    return dayOfWeekDto;
  }
}
