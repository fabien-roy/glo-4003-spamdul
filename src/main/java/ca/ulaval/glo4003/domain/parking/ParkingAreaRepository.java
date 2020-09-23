package ca.ulaval.glo4003.domain.parking;

public interface ParkingAreaRepository {
  ParkingAreaCode save(ParkingArea parkingArea);

  ParkingArea findByCode(ParkingAreaCode code);
}
