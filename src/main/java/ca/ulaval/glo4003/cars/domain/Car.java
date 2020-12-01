package ca.ulaval.glo4003.cars.domain;

public class Car {
  private final LicensePlate licensePlate;
  private final String manufacturer;
  private final String model;
  private final int year;
  private final ConsumptionType consumptionType;

  public ConsumptionType getConsumptionType() {
    return consumptionType;
  }

  public Car(
      LicensePlate licensePlate,
      String manufacturer,
      String model,
      int year,
      ConsumptionType consumptionType) {
    this.licensePlate = licensePlate;
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.consumptionType = consumptionType;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
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
}
