package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;

import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;

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
