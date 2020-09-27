package ca.ulaval.glo4003.api.car.dto;

public class CarDto {
  public String manufacturer;
  public String model;
  public int year;
  public String licensePlate;

  public CarDto(String manufacturer, String model, int year, String licensePlate) {
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.licensePlate = licensePlate;
  }
}
