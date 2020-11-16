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
  public Response enterWithAccessPass(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode);

    Response.Status status =
        accessStatusDto.accessStatus.equals(AccessStatus.ACCESS_GRANTED.toString())
            ? Response.Status.ACCEPTED
            : Response.Status.FORBIDDEN;

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response exitWithAccessPass(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    // On doit regarder que le char est encore là (pas sortie deux fois)
    // On doit retirer le char (il n'est plus là)
    return null;
  }
}
