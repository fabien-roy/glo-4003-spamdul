package ca.ulaval.glo4003.api.car;

import ca.ulaval.glo4003.api.car.dto.CarDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
public interface CarResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addCar(@PathParam("id") int accountId, CarDto carDTO);
}
