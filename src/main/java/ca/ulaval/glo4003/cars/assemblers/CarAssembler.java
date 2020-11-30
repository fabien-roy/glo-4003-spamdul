package ca.ulaval.glo4003.cars.assemblers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.cars.exceptions.InvalidManufacturerException;
import ca.ulaval.glo4003.cars.exceptions.InvalidModelException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CarAssembler {

  private final LicensePlateAssembler licensePlateAssembler;
  private final AccountIdAssembler accountIdAssembler;

  public CarAssembler(
      LicensePlateAssembler licensePlateAssembler, AccountIdAssembler accountIdAssembler) {
    this.licensePlateAssembler = licensePlateAssembler;
    this.accountIdAssembler = accountIdAssembler;
  }

  public List<CarDto> assemble(List<Car> cars) {
    return cars.stream().map(this::assemble).collect(Collectors.toList());
  }

  public CarDto assemble(Car car) {
    CarDto carDto = new CarDto();
    carDto.consumptionType = car.getConsumptionType().toString();
    carDto.licensePlate = car.getLicensePlate().toString();
    carDto.manufacturer = car.getManufacturer();
    carDto.model = car.getModel();
    carDto.year = car.getYear();
    return carDto;
  }

  public Car assemble(CarDto carDto, String accountId) {
    validateYear(carDto.year);
    validateNotNull(carDto);

    LicensePlate licensePlate = licensePlateAssembler.assemble(carDto.licensePlate);
    AccountId id = accountIdAssembler.assemble(accountId);

    return new Car(
        licensePlate,
        id,
        carDto.manufacturer,
        carDto.model,
        carDto.year,
        ConsumptionType.get(carDto.consumptionType));
  }

  private void validateNotNull(CarDto carDto) {
    if (carDto.manufacturer == null) {
      throw new InvalidManufacturerException();
    } else if (carDto.model == null) {
      throw new InvalidModelException();
    }
  }

  private void validateYear(int year) {
    // If the value is null it's parsed as a 0, so we can throw a null year exception
    if (year == 0) {
      throw new InvalidCarYearException();
    }
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      throw new InvalidCarYearException(currentYear);
    }
  }
}
