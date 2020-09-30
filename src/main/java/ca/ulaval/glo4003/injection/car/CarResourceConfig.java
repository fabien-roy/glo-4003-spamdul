package ca.ulaval.glo4003.injection.car;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.api.car.CarResource;
import ca.ulaval.glo4003.api.car.CarResourceImplementation;
import ca.ulaval.glo4003.domain.car.CarAssembler;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.LicensePlateAssembler;

public class CarResourceConfig {

  public CarResource createCarResource(AccountService accountService) {
    LicensePlateAssembler licensePlateAssembler = new LicensePlateAssembler();
    CarAssembler carAssembler = new CarAssembler(licensePlateAssembler);

    CarService carService = new CarService(carAssembler, accountService);

    return new CarResourceImplementation(carService);
  }
}
