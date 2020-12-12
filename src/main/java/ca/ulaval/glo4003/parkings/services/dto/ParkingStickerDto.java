package ca.ulaval.glo4003.parkings.services.dto;

public class ParkingStickerDto {
  public String parkingArea;
  public String receptionMethod;
  public String postalCode;
  public String email;
  public String parkingPeriod;

  @Override
  public String toString() {
    return String.format(
        "ParkingStickerDto{parkingArea='%s', receptionMethod='%s', postalCode='%s', email='%s', parkingPeriod='%s'}",
        parkingArea, receptionMethod, postalCode, email, parkingPeriod);
  }
}
