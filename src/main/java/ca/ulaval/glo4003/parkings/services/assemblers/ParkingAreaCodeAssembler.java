package ca.ulaval.glo4003.parkings.services.assemblers;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingAreaCodeException;

public class ParkingAreaCodeAssembler {
  public ParkingAreaCode assemble(String parkingAreaCode) {
    if (parkingAreaCode == null) throw new InvalidParkingAreaCodeException();

    return new ParkingAreaCode(parkingAreaCode);
  }
}
