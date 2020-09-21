package ca.ulaval.glo4003.api.Car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;
import javax.ws.rs.core.Response;

public class CarResourceImpl implements CarResource {

  private CarService carService;
  private CarValidator carValidator;

  public CarResourceImpl(CarService carService, CarValidator carValidator) {
    this.carService = carService;
    this.carValidator = carValidator;
  }

  @Override
  public Response addCar(int userId, CarDTO carDTO) {
    carValidator.validate(carDTO);

    carService.addCar(userId, carDTO);
    return Response.status(Response.Status.OK).build();
  }
}
