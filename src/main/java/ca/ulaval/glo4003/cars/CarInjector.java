package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.infrastructure.CarRepositoryInMemory;
import ca.ulaval.glo4003.cars.services.CarService;

public class CarInjector {
  private final CarRepository carRepository = new CarRepositoryInMemory();

  public CarService createCarService(AccountService accountService) {
    return new CarService(carRepository, accountService);
  }
}
