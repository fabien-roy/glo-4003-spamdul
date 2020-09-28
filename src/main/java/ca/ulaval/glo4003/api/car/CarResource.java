package ca.ulaval.glo4003.api.car;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cars")
public interface CarResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addCar(CarDto carDTO);
}
