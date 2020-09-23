package ca.ulaval.glo4003.infrastructure.parking;

import ca.ulaval.glo4003.domain.parking.ParkingArea;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingAreaException;
import java.util.HashMap;
import java.util.Map;

public class ParkingAreaRepositoryInMemory implements ParkingAreaRepository {
  private final Map<ParkingAreaCode, ParkingArea> parkingAreas = new HashMap<>();

  @Override
  public ParkingAreaCode save(ParkingArea parkingArea) {
    parkingAreas.put(parkingArea.getCode(), parkingArea);
    return parkingArea.getCode();
  }

  @Override
  public ParkingArea findByCode(ParkingAreaCode code) {
    ParkingArea foundParkingArea = parkingAreas.get(code);

    if (foundParkingArea == null) throw new NotFoundParkingAreaException();

    return foundParkingArea;
  }
}
