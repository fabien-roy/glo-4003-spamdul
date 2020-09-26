package ca.ulaval.glo4003.domain.car;

public class Car {

  private String manufacturer;
  private String model;
  private int year;
  private String licensePlate;

  public Car(String manufacturer, String model, int year, String licensePlate) {
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

  public String getLicensePlate() {
    return licensePlate;
  }
}
