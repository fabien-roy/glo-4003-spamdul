package ca.ulaval.glo4003.parkings.api.dto;

import java.util.List;

public class ParkingAreaDto {
  public String parkingArea;
  public List<ParkingPeriodPriceDto> parkingPeriodPrice;

  @Override
  public String toString() {
    return String.format("ParkingAreaDto{parkingArea='%s'}", parkingArea);
  }
}
