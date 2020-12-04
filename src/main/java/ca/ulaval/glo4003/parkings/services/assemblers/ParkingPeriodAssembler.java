package ca.ulaval.glo4003.parkings.services.assemblers;

import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;

public class ParkingPeriodAssembler {
  public ParkingPeriod assemble(String parkingPeriod) {
    return ParkingPeriod.get(parkingPeriod);
  }
}
