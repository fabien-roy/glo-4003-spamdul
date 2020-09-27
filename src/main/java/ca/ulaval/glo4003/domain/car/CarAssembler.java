package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.car.LicensePlate.LicensePlateAssembler;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import java.time.LocalDate;
import javax.inject.Inject;

public class CarAssembler {

  private LicensePlateAssembler licensePlateAssembler;

  @Inject
  public CarAssembler(LicensePlateAssembler licensePlateAssembler) {
    this.licensePlateAssembler = licensePlateAssembler;
  }

  public Car create(CarDto carDTO) {
    validateYear(carDTO.year);
    return new Car(
        carDTO.manufacturer,
        carDTO.model,
        carDTO.year,
        licensePlateAssembler.assemble(carDTO.licensePlate));
  }

  private void validateYear(int year) {
    int currentYear = LocalDate.now().getYear();
    if (currentYear + 1 <= year) {
      throw new InvalidCarYearException(currentYear);
    }
  }
}
