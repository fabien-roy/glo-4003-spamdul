package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.contact.dto.CarDTO;
import javax.ws.rs.core.Response;

public class CarResourceImpl implements CarResource {

  private CarService carService;
  private CarValidator carValidator;

  CarResourceImpl(CarService carService, CarValidator carValidator) {
    this.carService = carService;
    this.carValidator = carValidator;
  }

  @Override
  public Response addCar(int id, CarDTO carDTO) {
    carValidator.validate(carDTO);

    carService.addCar(carDTO);
    return Response.status(Response.Status.OK).build();
  }
}
