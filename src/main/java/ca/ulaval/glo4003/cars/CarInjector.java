package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.infrastructure.CarRepositoryInMemory;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.assemblers.LicensePlateAssembler;

public class CarInjector {
  private final CarRepository carRepository = new CarRepositoryInMemory();

  public CarService createCarService(
      AccountService accountService, AccountIdAssembler accountIdAssembler) {
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    CarAssembler carAssembler = new CarAssembler(licensePlateAssembler, accountIdAssembler);
    return new CarService(carAssembler, carRepository, accountService);
  }
}
