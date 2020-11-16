package ca.ulaval.glo4003.gateentries.api;

import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.services.GateEntryService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GateEntryResourceImplementation implements GateEntryResource {
  private final GateEntryService gateEntryService;

  public GateEntryResourceImplementation(GateEntryService gateEntryService) {
    this.gateEntryService = gateEntryService;
  }

  @Override
  public Response validateAccessPassWithCode(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassWithCode(dayOfWeekDto, accessPassCode);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response validateAccessPassWithLicensePlate(
      DayOfWeekDto dayOfWeekDto, String licensePlate) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassWithLicensePlate(dayOfWeekDto, licensePlate);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response exitWithAccessPass(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    // TODO : On fait .exit et si ça throw pas, retourner un Reponse.Status.OK
    return null;
  }

  @Override
  public Response exitWithLicensePlate(DayOfWeekDto dayOfWeekDto, String licensePlate) {
    // On doit regarder que le char est encore là (pas sortie deux fois)
    // On doit retirer le char (il n'est plus là)
    return null;
  }
}
