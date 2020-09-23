package ca.ulaval.glo4003.infrastructure.parking;

import ca.ulaval.glo4003.domain.parking.ParkingArea;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaRepository;

public class ParkingAreaRepositoryInMemory implements ParkingAreaRepository {
  @Override
  public ParkingAreaCode save(ParkingArea parkingArea) {
    // TODO : ParkingAreaRepositoryInMemory::save(ParkingArea)
    return null;
  }

  @Override
  public ParkingArea findByCode(ParkingAreaCode code) {
    // TODO : ParkingAreaRepositoryInMemory::findByCode(ParkingAreaCode)
    return null;
  }
}
