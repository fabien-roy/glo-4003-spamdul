package ca.ulaval.glo4003.parkings.domain;

import java.util.List;

public interface ParkingAreaRepository {
  ParkingAreaCode save(ParkingArea parkingArea);

  ParkingArea get(ParkingAreaCode code);

  List<ParkingAreaCode> getAllAreaCode();
}
