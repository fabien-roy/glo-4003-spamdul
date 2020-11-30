package ca.ulaval.glo4003.gates.api;

import ca.ulaval.glo4003.gates.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gates.services.GateService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.times.api.dto.DateTimeDto;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/gates")
public class GateResource {
  private final GateService gateService;

  public GateResource(GateService gateService) {
    this.gateService = gateService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithCode/{code}")
  public Response validateAccessPassEntryWithCode(
      @PathParam("code") String code, DateTimeDto dateTimeDto) {
    AccessStatusDto accessStatusDto =
        gateService.validateAccessPassEntryWithCode(dateTimeDto, code);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/entry/validateAccessPassWithLicensePlate/{licensePlate}")
  public Response validateAccessPassEntryWithLicensePlate(
      @PathParam("licensePlate") String licensePlate, DateTimeDto dateTimeDto) {
    AccessStatusDto accessStatusDto =
        gateService.validateAccessPassEntryWithLicensePlate(dateTimeDto, licensePlate);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/exit/validateAccessPassWithCode/{code}")
  public Response validateAccessPassExitWithCode(@PathParam("code") String code) {
    gateService.validateAccessPassExitWithCode(code);

    return Response.status(Response.Status.OK).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/exit/validateAccessPassWithLicensePlate/{licensePlate}")
  public Response validateAccessPassExitWithLicensePlate(
      @PathParam("licensePlate") String licensePlate) {
    gateService.validateAccessPassExitWithLicensePlate(licensePlate);

    return Response.status(Response.Status.OK).build();
  }
}
