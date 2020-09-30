package ca.ulaval.glo4003.api.car.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.domain.car.helpers.CarMother.*;
import static ca.ulaval.glo4003.domain.car.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.api.car.dto.CarDto;

public class CarBuilderDtoBuilder {
  private String accountId = createAccountId().toString();
  private String manufacturer = createManufacturer();
  private String model = createModel();
  private int year = createYear();
  private String licensePlate = createLicensePlate().toString();

  private CarBuilderDtoBuilder() {}

  public static CarBuilderDtoBuilder aCarDto() {
    return new CarBuilderDtoBuilder();
  }

  public CarBuilderDtoBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public CarBuilderDtoBuilder withLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public CarDto build() {
    CarDto carDto = new CarDto();
    carDto.accountId = accountId;
    carDto.manufacturer = manufacturer;
    carDto.model = model;
    carDto.year = year;
    carDto.licensePlate = licensePlate;
    return carDto;
  }
}
