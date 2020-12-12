package ca.ulaval.glo4003.cars.services.converters;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidManufacturerException;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidModelException;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import java.time.LocalDate;

public class CarConverter {

  private final LicensePlateConverter licensePlateConverter;

  public CarConverter(LicensePlateConverter licensePlateConverter) {
    this.licensePlateConverter = licensePlateConverter;
  }

  public Car convert(CarDto carDto) {
    validateYear(carDto.year);
    validateNotNull(carDto);

    LicensePlate licensePlate = licensePlateConverter.convert(carDto.licensePlate);

    return new Car(
        licensePlate,
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
