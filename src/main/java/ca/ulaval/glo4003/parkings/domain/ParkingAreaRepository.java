package ca.ulaval.glo4003.parkings.domain;

public interface ParkingAreaRepository {
  ParkingAreaCode save(ParkingArea parkingArea);

  ParkingArea get(ParkingAreaCode code);
}
