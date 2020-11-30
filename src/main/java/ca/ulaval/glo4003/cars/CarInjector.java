package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.infrastructure.CarRepositoryInMemory;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.converters.CarConverter;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;

public class CarInjector {
  private final CarRepository carRepository = new CarRepositoryInMemory();

  public CarService createCarService(
      AccountService accountService, AccountIdConverter accountIdConverter) {
    LicensePlateConverter licensePlateConverter = new LicensePlateConverter();
    CarConverter carConverter = new CarConverter(licensePlateConverter, accountIdConverter);
    CarAssembler carAssembler = new CarAssembler();
    return new CarService(carConverter, carAssembler, carRepository, accountService);
  }
}
