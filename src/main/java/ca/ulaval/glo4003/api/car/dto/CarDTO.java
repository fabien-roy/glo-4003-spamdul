package ca.ulaval.glo4003.api.car.dto;

public class CarDTO {
  private String manufacturer;
  private String model;
  private int year;
  private String licensePlate;

  public CarDTO(String manufacturer, String model, int year, String licensePlate) {
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
