package ca.ulaval.glo4003.cars.domain;

public interface CarRepository {
  LicensePlate save(Car car);

  Car getCarByLicensePlate(LicensePlate licensePlate);
}
