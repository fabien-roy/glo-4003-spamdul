package ca.ulaval.glo4003.cars.services.assemblers;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import java.util.List;
import java.util.stream.Collectors;

public class CarAssembler {

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
}
