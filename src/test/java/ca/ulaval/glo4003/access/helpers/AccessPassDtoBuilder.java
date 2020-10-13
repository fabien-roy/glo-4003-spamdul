package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;

public class AccessPassDtoBuilder {
  private String accessDay = createDay().toString();
  private String licensePlate = createLicensePlate().toString();

  public static AccessPassDtoBuilder anAccessPassDto() {
    return new AccessPassDtoBuilder();
  }

  public AccessPassDtoBuilder withLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public AccessPassDto build() {
    AccessPassDto accessPassDto = new AccessPassDto();
    accessPassDto.accessDay = accessDay;
    accessPassDto.licensePlate = licensePlate;
    return accessPassDto;
  }
}
