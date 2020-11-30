package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.infrastructure.CarRepositoryInMemory;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.assemblers.LicensePlateAssembler;

public class CarInjector {
  private final CarRepository carRepository = new CarRepositoryInMemory();

  public CarService createCarService(
      AccountService accountService, AccountIdConverter accountIdConverter) {
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    CarAssembler carAssembler = new CarAssembler(licensePlateAssembler, accountIdConverter);
    return new CarService(carAssembler, carRepository, accountService);
  }
}
