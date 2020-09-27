package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import javax.inject.Inject;

public class CarAssembler {

  private CarValidator carValidator;

  @Inject
  public CarAssembler(CarValidator carValidator) {
    this.carValidator = carValidator;
  }

  public Car create(CarDto carDTO) {
    carValidator.validate(carDTO);
    return new Car(
        carDTO.getManufacturer(), carDTO.getModel(), carDTO.getYear(), carDTO.getLicensePlate());
  }
}
