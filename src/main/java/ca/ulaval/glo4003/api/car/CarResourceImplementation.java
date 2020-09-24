package ca.ulaval.glo4003.api.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import javax.ws.rs.core.Response;

public class CarResourceImplementation implements CarResource {

  private CarService carService;

  public CarResourceImplementation(CarService carService) {
    this.carService = carService;
  }

  @Override
  public Response addCar(int userId, CarDTO carDTO) {
    carService.addCar(userId, carDTO);
    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response test() {
    // TODO : added for API testing purposes, should be removed
    throw new InvalidCarYearException("11111", "22222");
  }
}
