package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.cars.helpers.CarMother.*;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;

public class CarBuilder {
  private LicensePlate licensePlate = createLicensePlate();
  private String manufacturer = createManufacturer();
  private String model = createModel();
  private int year = createYear();
  private ConsumptionType consumptionType = createNotZeroPullutionConsumptionTypes();

  private CarBuilder() {}

  public static CarBuilder aCar() {
    return new CarBuilder();
  }

  public CarBuilder withLicensePlate(LicensePlate licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public Car build() {
    return new Car(licensePlate, manufacturer, model, year, consumptionType);
  }
}
