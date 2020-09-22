package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import ca.ulaval.glo4003.domain.Account.AccountService;

public class CarService {

  private CarAssembler carAssembler;
  private AccountService accountService;

  public CarService(CarAssembler carAssembler, AccountService accountService) {
    this.carAssembler = carAssembler;
    this.accountService = accountService;
  }

  public void addCar(int accountId, CarDTO carDTO) {
    Car car = carAssembler.createCar(carDTO);

    accountService.addCarToAccount(accountId, car);
  }
}
