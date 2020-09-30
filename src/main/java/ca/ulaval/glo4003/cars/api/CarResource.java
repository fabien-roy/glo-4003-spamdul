package ca.ulaval.glo4003.cars.api;

import ca.ulaval.glo4003.cars.api.dto.CarDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cars")
public interface CarResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addCar(CarDto carDto);
}
