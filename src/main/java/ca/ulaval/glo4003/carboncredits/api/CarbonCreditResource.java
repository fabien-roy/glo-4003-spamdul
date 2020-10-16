package ca.ulaval.glo4003.carboncredits.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/carbonCredits")
public interface CarbonCreditResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getCarbonCredits();
}
