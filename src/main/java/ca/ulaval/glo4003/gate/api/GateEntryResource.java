package ca.ulaval.glo4003.gate.api;

import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/gate")
public interface GateEntryResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithCode/{code}")
  Response validateAccessPassEntryWithCode(
      DayOfWeekDto dayOfWeekDto, @PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithLicensePlate/{licensePlate}")
  Response validateAccessPassEntryWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, @PathParam("licensePlate") String licensePlate);

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
