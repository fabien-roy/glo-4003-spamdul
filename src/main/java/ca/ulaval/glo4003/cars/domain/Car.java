package ca.ulaval.glo4003.cars.domain;

public class Car {

  private String manufacturer;
  private String model;
  private int year;
  private LicensePlate licensePlate;

  public Car(String manufacturer, String model, int year, LicensePlate licensePlate) {
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.licensePlate = licensePlate;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
  }
}
