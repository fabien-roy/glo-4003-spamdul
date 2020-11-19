package ca.ulaval.glo4003.gates.helpers;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeBuilder.aDateTime;

import ca.ulaval.glo4003.gates.api.dto.DateTimeDto;

public class DateTimeDtoBuilder {
  private String dateTime = aDateTime().toString();

  private DateTimeDtoBuilder() {}

  public static DateTimeDtoBuilder aDateTimeDto() {
    return new DateTimeDtoBuilder();
  }

  public DateTimeDto build() {
    DateTimeDto dateTimeDto = new DateTimeDto();
    dateTimeDto.dateTime = dateTime;
    return dateTimeDto;
  }
}
