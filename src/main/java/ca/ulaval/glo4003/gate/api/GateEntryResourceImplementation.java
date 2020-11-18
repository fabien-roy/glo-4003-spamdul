package ca.ulaval.glo4003.gate.api;

import ca.ulaval.glo4003.gate.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gate.services.GateEntryService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GateEntryResourceImplementation implements GateEntryResource {
  private final GateEntryService gateEntryService;

  public GateEntryResourceImplementation(GateEntryService gateEntryService) {
    this.gateEntryService = gateEntryService;
  }

  @Override
  public Response validateAccessPassEntryWithCode(
      DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response validateAccessPassEntryWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, String licensePlate) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithLicensePlate(dayOfWeekDto, licensePlate);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response validateAccessPassExitWithCode(String accessPassCode) {
    gateEntryService.validateAccessPassExitWithCode(accessPassCode);

    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response validateAccessPassExitWithLicensePlate(String licensePlate) {
    gateEntryService.validateAccessPassExitWithLicensePlate(licensePlate);

    return Response.status(Response.Status.OK).build();
  }
}
