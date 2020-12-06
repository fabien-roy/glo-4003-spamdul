package ca.ulaval.glo4003.cars.services;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.converters.CarConverter;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import java.util.List;

public class CarService {

  private final CarConverter carConverter;
  private final CarAssembler carAssembler;
  private final AccountService accountService;

  public CarService(
      CarConverter carConverter, CarAssembler carAssembler, AccountService accountService) {
    this.carConverter = carConverter;
    this.carAssembler = carAssembler;
    this.accountService = accountService;
  }

  public void addCar(CarDto carDto, String accountId) {
    Account account = accountService.getAccount(accountId);
    Car car = carConverter.convert(carDto);

    accountService.addCarToAccount(account.getId(), car);
  }

  public Car getCar(LicensePlate licensePlate) {
    return accountService.getCar(licensePlate);
  }

  public List<CarDto> getCars(String accountId) {
    Account account = accountService.getAccount(accountId);
    List<Car> cars = account.getCars();

    return carAssembler.assemble(cars);
  }
}
