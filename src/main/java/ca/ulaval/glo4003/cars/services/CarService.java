package ca.ulaval.glo4003.cars.services;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

  private final CarAssembler carAssembler;
  private final CarRepository carRepository;
  private final AccountService accountService;

  public CarService(
      CarAssembler carAssembler, CarRepository carRepository, AccountService accountService) {
    this.carAssembler = carAssembler;
    this.carRepository = carRepository;
    this.accountService = accountService;
  }

  public void addCar(CarDto carDto, String accountId) {
    Car car = carAssembler.assemble(carDto, accountId);

    accountService.addLicensePlateToAccount(car.getAccountId(), car.getLicensePlate());

    carRepository.save(car);
  }

  public Car getCar(LicensePlate licensePlate) {
    return carRepository.get(licensePlate);
  }

  public List<CarDto> getCars(String accountId) {
    Account account = accountService.getAccount(accountId);
    List<LicensePlate> licensePlates = account.getLicensePlates();
    List<Car> cars = licensePlates.stream().map(this::getCar).collect(Collectors.toList());

    return carAssembler.assemble(cars);
  }
}
