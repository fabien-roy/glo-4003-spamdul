package ca.ulaval.glo4003.gates.api;

import ca.ulaval.glo4003.gates.api.dto.DateTimeDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/gates")
public interface GateResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithCode/{code}")
  Response validateAccessPassEntryWithCode(
      DateTimeDto dateTimeDto, @PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithLicensePlate/{licensePlate}")
  Response validateAccessPassEntryWithLicensePlate(
      DateTimeDto dateTimeDto, @PathParam("licensePlate") String licensePlate);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/exit/validateAccessPassWithCode/{code}")
  Response validateAccessPassExitWithCode(@PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/exit/validateAccessPassWithLicensePlate/{licensePlate}")
  Response validateAccessPassExitWithLicensePlate(@PathParam("licensePlate") String licensePlate);
}
