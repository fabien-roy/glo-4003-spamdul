package ca.ulaval.glo4003.initiatives.api;

import ca.ulaval.glo4003.initiatives.api.dto.*;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InitiativeResourceImplementation implements InitiativeResource {
  private final InitiativeService initiativeService;

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
    InitiativeAvailableAmountDto initiativeAvailableAmountDto =
        initiativeService.getAvailableAmount();
    return Response.status(Response.Status.OK)
        .entity(initiativeAvailableAmountDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response getInitiative(String initiativeCode) {
    InitiativeDto initiativeDto = initiativeService.getInitiative(initiativeCode);
    return Response.status(Response.Status.OK)
        .entity(initiativeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @Override
  public Response allocateAmountToInitiative(
      String initiativeCode, InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDTO) {
    initiativeService.addAllocatedAmountToInitiative(
        initiativeCode, initiativeAddAllocatedAmountDTO);
    return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
  }
}
