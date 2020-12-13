package ca.ulaval.glo4003.cars;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;

public class CarInjector {

  public CarService createCarService(AccountService accountService) {
    return new CarService(accountService);
  }
}
