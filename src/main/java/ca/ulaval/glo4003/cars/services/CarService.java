package ca.ulaval.glo4003.cars.services;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.domain.Car;

public class CarService {

  private final CarAssembler carAssembler;
  private final AccountService accountService;

  public CarService(CarAssembler carAssembler, AccountService accountService) {
    this.carAssembler = carAssembler;
    this.accountService = accountService;
  }

  public void addCar(CarDto carDto) {
    Car car = carAssembler.create(carDto);

    accountService.addLicensePlateToAccount(car.getAccountId(), car.getLicensePlate());
  }
}
