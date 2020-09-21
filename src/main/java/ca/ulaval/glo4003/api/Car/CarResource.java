package ca.ulaval.glo4003.api.Car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
public interface CarResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addCar(@PathParam("id") int accountId, CarDTO carDTO);
}
