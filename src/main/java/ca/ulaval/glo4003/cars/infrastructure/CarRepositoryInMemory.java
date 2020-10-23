package ca.ulaval.glo4003.cars.infrastructure;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.NotFoundLicensePlateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarRepositoryInMemory implements CarRepository {
  private final Map<LicensePlate, Car> cars = new HashMap<>();

  @Override
  public LicensePlate save(Car car) {
    cars.put(car.getLicensePlate(), car);
    return car.getLicensePlate();
  }

  @Override
  public Car get(LicensePlate licensePlate) {
    Car car = cars.get(licensePlate);

    if (car == null) throw new NotFoundLicensePlateException();

    return car;
  }

  @Override
  public List<Car> getCars(List<LicensePlate> licensePlates) {
    return licensePlates.stream().map(this::get).collect(Collectors.toList());
  }
}
