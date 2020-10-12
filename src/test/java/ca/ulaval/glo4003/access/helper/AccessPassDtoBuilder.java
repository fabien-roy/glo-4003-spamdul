package ca.ulaval.glo4003.access.helper;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.access.api.dto.AccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.Days;

public class AccessPassDtoBuilder {
  private Days accessDay = createDay();
  private LicensePlate licensePlate = createLicensePlate();

  public static AccessPassDtoBuilder anAccessPassDto() {
    return new AccessPassDtoBuilder();
  }

  public AccessPassDto build() {
    AccessPassDto accessPassDto = new AccessPassDto();
    accessPassDto.accessDay = accessDay.toString();
    accessPassDto.licensePlate = licensePlate.toString();

    return accessPassDto;
  }
}
