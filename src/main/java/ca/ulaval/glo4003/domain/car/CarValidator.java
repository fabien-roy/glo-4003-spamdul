package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;

public class CarValidator {

  public CarValidator() {}

  public void validate(CarDTO carDTO) {
    // TODO : Validate licensePlate and year
    if (carDTO.getLicensePlate() == "") {
      throw new InvalidCarException("Temp");
    }
  }
}
