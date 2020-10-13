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
  public Response validateAccessPass(DayOfWeekDto dayOfWeekDto, String accessPassCode) {
    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode);

    Response.Status status;
    if (AccessStatus.ACCESS_REFUSED.toString().equals(accessStatusDto.accessStatus)) {
      status = Response.Status.FORBIDDEN;
    } else {
      status = Response.Status.ACCEPTED;
    }

    return Response.status(status).entity(accessStatusDto).type(MediaType.APPLICATION_JSON).build();
  }
}
