package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;

public class CarAssembler {

  private CarValidator carValidator;

  public CarAssembler(CarValidator carValidator) {
    this.carValidator = carValidator;
  }

  public Car create(CarDTO carDTO) {
    carValidator.validate(carDTO);
    return new Car(
        carDTO.getManufacturer(), carDTO.getModel(), carDTO.getYear(), carDTO.getLicensePlate());
  }
}
