package ca.ulaval.glo4003.domain.car.helpers;

import static ca.ulaval.glo4003.domain.car.helpers.CarMother.*;
import static ca.ulaval.glo4003.domain.car.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.domain.car.Car;
import ca.ulaval.glo4003.domain.car.LicensePlate;

public class CarBuilder {
  private String manufacturer = createManufacturer();
  private String model = createModel();
  private int year = createYear();
  private LicensePlate licensePlate = createLicensePlate();

  private CarBuilder() {}

  public static CarBuilder aCar() {
    return new CarBuilder();
  }

  public Car build() {
    Car car = new Car(manufacturer, model, year, licensePlate);
    return car;
  }
}
