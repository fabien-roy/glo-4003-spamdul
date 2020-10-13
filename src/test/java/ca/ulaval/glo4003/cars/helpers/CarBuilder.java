package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarMother.*;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.domain.LicensePlate;

public class CarBuilder {
  private LicensePlate licensePlate = createLicensePlate();
  private AccountId accountId = createAccountId();
  private String manufacturer = createManufacturer();
  private String model = createModel();
  private int year = createYear();
  private ConsumptionTypes consumptionTypes = createNotZeroPullutionConsumptionTypes();

  private CarBuilder() {}

  public static CarBuilder aCar() {
    return new CarBuilder();
  }

  public Car build() {
    return new Car(licensePlate, accountId, manufacturer, model, year, consumptionTypes);
  }
}
