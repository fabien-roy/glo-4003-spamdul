package ca.ulaval.glo4003.gates.api;

import ca.ulaval.glo4003.gates.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gates.services.GateService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.times.api.dto.DateTimeDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GateResourceImplementation implements GateResource {
  private final GateService gateService;

  public GateResourceImplementation(GateService gateService) {
    this.gateService = gateService;
  }

  @Override
  public Response validateAccessPassEntryWithCode(DateTimeDto dateTimeDto, String accessPassCode) {
    AccessStatusDto accessStatusDto =
        gateService.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response validateAccessPassEntryWithLicensePlate(
      DateTimeDto dateTimeDto, String licensePlate) {
    AccessStatusDto accessStatusDto =
        gateService.validateAccessPassEntryWithLicensePlate(dateTimeDto, licensePlate);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response validateAccessPassExitWithCode(String accessPassCode) {
    gateService.validateAccessPassExitWithCode(accessPassCode);

    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response validateAccessPassExitWithLicensePlate(String licensePlate) {
    gateService.validateAccessPassExitWithLicensePlate(licensePlate);

    return Response.status(Response.Status.OK).build();
  }
}
