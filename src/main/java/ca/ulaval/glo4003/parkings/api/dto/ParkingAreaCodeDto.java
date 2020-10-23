package ca.ulaval.glo4003.parkings.api.dto;

public class ParkingAreaCodeDto {
  public String parkingArea;

  @Override
  public String toString() {
    return String.format("ParkingAreaDto{parkingArea='%s'}", parkingArea);
  }
}
