package ca.ulaval.glo4003.injection.car;

import ca.ulaval.glo4003.api.car.CarResource;
import ca.ulaval.glo4003.api.car.CarResourceImplementation;
import ca.ulaval.glo4003.domain.account.AccountService;
import ca.ulaval.glo4003.domain.car.CarAssembler;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;

public class CarResourceConfig {

  public CarResource createCarResource(AccountService accountService) {
    CarValidator carValidator = new CarValidator();

    CarAssembler carAssembler = new CarAssembler(carValidator);

    CarService carService = new CarService(carAssembler, accountService);

    return new CarResourceImplementation(carService);
  }
}
