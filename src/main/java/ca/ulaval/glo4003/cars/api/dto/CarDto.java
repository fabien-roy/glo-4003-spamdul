package ca.ulaval.glo4003.cars.api.dto;

public class CarDto {
  public String manufacturer;
  public String model;
  public int year;
  public String licensePlate;
  public String consumptionType;

  @Override
  public String toString() {
    return String.format(
        "CarDto{manufacturer='%s', model='%s', year='%d', licensePlate='%s', consumptionType='%s'}",
        manufacturer, model, year, licensePlate, consumptionType);
  }
}
