package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.api.car.dto.CarDto;

public class CarService {

  private final CarAssembler carAssembler;
  private final AccountService accountService;

  public CarService(CarAssembler carAssembler, AccountService accountService) {
    this.carAssembler = carAssembler;
    this.accountService = accountService;
  }

  public void addCar(CarDto carDto) {
    Car car = carAssembler.create(carDto);

    accountService.addCarToAccount(carDto.accountId, car);
  }
}
