package ca.ulaval.glo4003.cars.helpers;

import static ca.ulaval.glo4003.cars.helpers.CarMother.*;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;

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
