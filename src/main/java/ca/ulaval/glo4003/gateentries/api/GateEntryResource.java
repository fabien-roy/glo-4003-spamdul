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
  @Path("/entry/validateAccessPassWithCode/{code}")
  Response validateAccessPassWithCode(
      DayOfWeekDto dayOfWeekDto, @PathParam("code") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithLicensePlate/{licensePlate}")
  Response validateAccessPassWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, @PathParam("licensePlate") String licensePlate);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON) // TODO : I have no idea what this returns
  @Path("/exit/withAccessPass/{accessPassCode}")
  Response exitWithAccessPass(
      DayOfWeekDto dayOfWeekDto, @PathParam("accessPassCode") String accessPassCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON) // TODO : I have no idea what this returns
  @Path("/exit/withLicensePlate/{licensePlate}")
  Response exitWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, @PathParam("licensePlate") String licensePlate);
}
