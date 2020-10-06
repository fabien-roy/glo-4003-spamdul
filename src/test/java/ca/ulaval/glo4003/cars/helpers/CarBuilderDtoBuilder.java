package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarMother.*;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.cars.api.dto.CarDto;

public class CarBuilderDtoBuilder {
  private String licensePlate = createLicensePlate().toString();
  private String accountId = createAccountId().toString();
  private String manufacturer = createManufacturer();
  private String model = createModel();
  private int year = createYear();

  private CarBuilderDtoBuilder() {}

  public static CarBuilderDtoBuilder aCarDto() {
    return new CarBuilderDtoBuilder();
  }

  public CarBuilderDtoBuilder withLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public CarBuilderDtoBuilder withAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public CarBuilderDtoBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public CarDto build() {
    CarDto carDto = new CarDto();
    carDto.licensePlate = licensePlate;
    carDto.accountId = accountId;
    carDto.manufacturer = manufacturer;
    carDto.model = model;
    carDto.year = year;
    return carDto;
  }
}
