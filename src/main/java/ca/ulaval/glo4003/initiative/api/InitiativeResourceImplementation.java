package ca.ulaval.glo4003.initiative.api;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.services.InitiativeService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
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
  public Response getAllInitiatives() {
    List<InitiativeDto> initiativesDto = initiativeService.getAllInitiatives();
    GenericEntity<List<InitiativeDto>> entities =
        new GenericEntity<List<InitiativeDto>>(initiativesDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getInitiativeAvailableAmount() {
    // TODO go find available mount in bank system;
    return null;
  }

  @Override
  public Response getInitiative(String initiativeCode) {
    InitiativeDto initiativeDto = initiativeService.getInitiative(initiativeCode);
    return Response.status(Response.Status.FOUND)
        .entity(initiativeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response allocateAmountToInitiative(
      String initiativeCode, InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDTO) {
    initiativeService.AddAllocatedAmountToInitiative(
        initiativeCode, initiativeAddAllocatedAmountDTO);
    // TODO should we return something when adding amount to initiative?
    return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
  }
}
