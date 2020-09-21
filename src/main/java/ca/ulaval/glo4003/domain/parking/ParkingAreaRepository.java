package ca.ulaval.glo4003.domain.parking;

public interface ParkingAreaRepository {
  ParkingAreaCode findByCode(ParkingAreaCode code);
}
