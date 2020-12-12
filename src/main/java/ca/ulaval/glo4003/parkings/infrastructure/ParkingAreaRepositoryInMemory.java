package ca.ulaval.glo4003.parkings.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.exceptions.NotFoundParkingAreaException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingAreaRepositoryInMemory implements ParkingAreaRepository {
  private final Map<ParkingAreaCode, ParkingArea> parkingAreas = new HashMap<>();

  @Override
  public ParkingAreaCode save(ParkingArea parkingArea) {
    parkingAreas.put(parkingArea.getCode(), parkingArea);
    return parkingArea.getCode();
  }

  @Override
  public ParkingArea get(ParkingAreaCode code) {
    ParkingArea foundParkingArea = parkingAreas.get(code);

    if (foundParkingArea == null) throw new NotFoundParkingAreaException();

    return foundParkingArea;
  }

  @Override
  public List<ParkingArea> getAll() {
    return new ArrayList<>(parkingAreas.values());
  }
}
