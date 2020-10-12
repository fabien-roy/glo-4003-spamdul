package ca.ulaval.glo4003.cars.assemblers;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.api.dto.CarDto;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypes;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidCarYearException;
import java.time.LocalDate;

public class CarAssembler {

  private final LicensePlateAssembler licensePlateAssembler;
  private final AccountIdAssembler accountIdAssembler;

  public CarAssembler(
      LicensePlateAssembler licensePlateAssembler, AccountIdAssembler accountIdAssembler) {
    this.licensePlateAssembler = licensePlateAssembler;
    this.accountIdAssembler = accountIdAssembler;
  }

  public Car create(CarDto carDto) {
    validateYear(carDto.year);

    LicensePlate licensePlate = licensePlateAssembler.assemble(carDto.licensePlate);
    AccountId accountId = accountIdAssembler.assemble(carDto.accountId);

    return new Car(
        licensePlate,
        accountId,
        carDto.manufacturer,
        carDto.model,
        carDto.year,
        ConsumptionTypes.get(carDto.consumptionType));
  }

  private void validateYear(int year) {
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      throw new InvalidCarYearException(currentYear);
    }
  }
}
