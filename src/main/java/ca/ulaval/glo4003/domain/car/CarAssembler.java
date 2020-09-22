package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;

public class CarAssembler {

  public Car create(CarDTO carDTO) {
    return new Car(
        carDTO.getManufacturer(), carDTO.getModel(), carDTO.getYear(), carDTO.getLicensePlate());
  }
}
