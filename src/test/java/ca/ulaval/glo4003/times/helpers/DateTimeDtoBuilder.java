package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;

import ca.ulaval.glo4003.times.api.dto.DateTimeDto;

public class DateTimeDtoBuilder {

  private String dateTime = createDateTime().toString();

  public static DateTimeDtoBuilder aDateTimeDto() {
    return new DateTimeDtoBuilder();
  }

  public DateTimeDtoBuilder withDateTime(String dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  public DateTimeDto build() {
    DateTimeDto dateTimeDto = new DateTimeDto();
    dateTimeDto.dateTime = dateTime;
    return dateTimeDto;
  }
}
