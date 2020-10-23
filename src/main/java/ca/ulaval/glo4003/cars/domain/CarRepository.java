package ca.ulaval.glo4003.cars.domain;

import java.util.List;

public interface CarRepository {
  LicensePlate save(Car car);

  Car get(LicensePlate licensePlate);

  List<Car> getCars(List<LicensePlate> licensePlates);
}
