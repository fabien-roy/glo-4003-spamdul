package ca.ulaval.glo4003.parkings.services.dto;

public class ParkingPeriodPriceDto {
  public String period;
  public Double price;

  @Override
  public String toString() {
    return String.format("ParkingPeriodPriceDto{period='%s', price='%s'}", period, price);
  }
}
