package ca.ulaval.glo4003.gateentries.api;

import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/gateEntry")
public interface GateEntryResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassEntryWithCode/{code}")
  Response validateAccessPassEntryWithCode(
      DayOfWeekDto dayOfWeekDto, @PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassEntryWithLicensePlate/{licensePlate}")
  Response validateAccessPassEntryWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, @PathParam("licensePlate") String licensePlate);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON) // TODO : I have no idea what this returns
  @Path("/exit/validateAccessPassEntryWithCode/{code}")
  Response validateAccessPassExitWithCode(@PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON) // TODO : I have no idea what this returns
  @Path("/exit/validateAccessPassEntryWithLicensePlate/{licensePlate}")
  Response validateAccessPassExitWithLicensePlate(@PathParam("licensePlate") String licensePlate);
}
