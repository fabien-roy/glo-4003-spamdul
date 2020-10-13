package ca.ulaval.glo4003.cars.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;

public class Car {

  private final LicensePlate licensePlate;
  private final AccountId accountId;
  private final String manufacturer;
  private final String model;
  private final int year;
  private final ConsumptionType consumptionType;

  public ConsumptionType getConsumptionType() {
    return consumptionType;
  }

  public Car(
      LicensePlate licensePlate,
      AccountId accountId,
      String manufacturer,
      String model,
      int year,
      ConsumptionType consumptionType) {
    this.licensePlate = licensePlate;
    this.accountId = accountId;
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.consumptionType = consumptionType;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  public AccountId getAccountId() {
    return accountId;
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
