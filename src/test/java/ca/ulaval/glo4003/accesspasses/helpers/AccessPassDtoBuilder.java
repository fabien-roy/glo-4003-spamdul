package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.DayOfWeekMother.createDayOfWeek;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;

public class AccessPassDtoBuilder {
  private String accessDay = createDayOfWeek().toString();
  private String licensePlate = createLicensePlate().toString();
  private AccessPeriod accessPeriod = AccessPeriod.ONE_SEMESTER;
  private String[] semesters;

  public static AccessPassDtoBuilder anAccessPassDto() {
    return new AccessPassDtoBuilder();
  }

  public AccessPassDtoBuilder withAccessDay(String accessDay) {
    this.accessDay = accessDay;
    return this;
  }

  public AccessPassDtoBuilder withLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public AccessPassDtoBuilder withAccessPeriod(AccessPeriod accessPeriod) {
    this.accessPeriod = accessPeriod;
    return this;
  }

  public AccessPassDtoBuilder withSemesters(String[] semesters) {
    this.semesters = semesters;
    return this;
  }

  public AccessPassDto build() {
    AccessPassDto accessPassDto = new AccessPassDto();
    accessPassDto.accessDay = accessDay;
    accessPassDto.licensePlate = licensePlate;
    accessPassDto.period = accessPeriod.toString();
    accessPassDto.semesters = semesters;
    return accessPassDto;
  }
}
