package ca.ulaval.glo4003.initiative.api;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDTO;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiative.services.InitiativeService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InitiativeResourceImplementation implements InitiativeResource {
  private InitiativeService initiativeService;

  public InitiativeResourceImplementation(InitiativeService initiativeService) {
    this.initiativeService = initiativeService;
  }

  @Override
  public Response addInitiative(AddInitiativeDto addInitiativeDto) {
    InitiativeCodeDto initiativeCodeDto = initiativeService.addInitiative(addInitiativeDto);
    return Response.status(Response.Status.CREATED)
        .entity(initiativeCodeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getInitiatives() {
    return null;
  }

  @Override
  public Response getInitiativeAvailableAmount() {
    return null;
  }

  @Override
  public Response allocateAmountToInitiative(
      String initiativeCode, InitiativeAddAllocatedAmountDTO initiativeAddAllocatedAmountDTO) {
    return null;
  }
}
