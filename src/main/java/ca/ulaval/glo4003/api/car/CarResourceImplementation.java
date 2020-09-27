package ca.ulaval.glo4003.api.car;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import ca.ulaval.glo4003.domain.car.CarService;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class CarResourceImplementation implements CarResource {

  private final CarService carService;

  @Inject
  public CarResourceImplementation(CarService carService) {
    this.carService = carService;
  }

  @Override
  public Response addCar(int userId, CarDto carDTO) {
    carService.addCar(userId, carDTO);
    return Response.status(Response.Status.CREATED).build();
  }
}
