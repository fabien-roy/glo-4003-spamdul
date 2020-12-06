package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.converters.CarConverter;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;

public class CarInjector {

  public CarService createCarService(AccountService accountService) {
    LicensePlateConverter licensePlateConverter = new LicensePlateConverter();
    CarConverter carConverter = new CarConverter(licensePlateConverter);
    CarAssembler carAssembler = new CarAssembler();
    return new CarService(carConverter, carAssembler, accountService);
  }
}
