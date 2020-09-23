package ca.ulaval.glo4003.domain.parking;

public class ParkingArea {
  private ParkingAreaCode code;

  public ParkingArea(ParkingAreaCode code) {
    this.code = code;
  }

  public ParkingAreaCode getCode() {
    return code;
  }
}
