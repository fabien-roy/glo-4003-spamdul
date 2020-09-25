package ca.ulaval.glo4003.domain.parking.helpers;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;

import ca.ulaval.glo4003.domain.parking.ParkingArea;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;

public class ParkingAreaBuilder {
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  private ParkingAreaBuilder() {}

  public static ParkingAreaBuilder aParkingArea() {
    return new ParkingAreaBuilder();
  }

  public ParkingArea build() {
    return new ParkingArea(parkingAreaCode);
  }
}
