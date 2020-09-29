package ca.ulaval.glo4003.api.car.dto;

public class CarDto {
  public String accountId;
  public String manufacturer;
  public String model;
  public int year;
  public String licensePlate;

  @Override
  public String toString() {
    return String.format(
        "CarDto{accountId='%s', manufacturer='%s', model='%s', year='%d', licensePlate='%s'}",
        accountId, manufacturer, model, year, licensePlate);
  }
}
