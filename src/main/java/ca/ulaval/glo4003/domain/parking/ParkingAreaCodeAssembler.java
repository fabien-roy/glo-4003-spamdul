package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.parking.exception.InvalidParkingAreaCodeException;

public class ParkingAreaCodeAssembler {
  public ParkingAreaCode assemble(String parkingAreaCode) {
    if (parkingAreaCode == null) throw new InvalidParkingAreaCodeException();

    return new ParkingAreaCode(parkingAreaCode);
  }
}
