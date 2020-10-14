package ca.ulaval.glo4003.gateentries.api.helpers;

import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;

public class DayOfWeekDtoBuilder {
  private String dayOfWeek = createDay().toString();

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
