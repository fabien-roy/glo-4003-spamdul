package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.CarResource;
import ca.ulaval.glo4003.cars.api.CarResourceImplementation;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.infrastructure.CarRepositoryInMemory;
import ca.ulaval.glo4003.cars.services.CarService;

public class CarInjector {

  private final CarRepository carRepository = new CarRepositoryInMemory();

  public CarResource createCarResource(
      AccountService accountService, AccountIdAssembler accountIdAssembler) {
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    CarAssembler carAssembler = new CarAssembler(licensePlateAssembler, accountIdAssembler);

    CarService carService = new CarService(carAssembler, carRepository, accountService);

    return new CarResourceImplementation(carService);
  }
}
