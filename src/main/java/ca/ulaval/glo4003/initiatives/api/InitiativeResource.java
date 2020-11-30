package ca.ulaval.glo4003.initiatives.api;

import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import ca.ulaval.glo4003.initiatives.services.dto.*;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/initiatives")
public class InitiativeResource {
  private final InitiativeService initiativeService;

  public InitiativeResource(InitiativeService initiativeService) {
    this.initiativeService = initiativeService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addInitiative(AddInitiativeDto addInitiativeDto) {
    InitiativeCodeDto initiativeCodeDto = initiativeService.addInitiative(addInitiativeDto);
    return Response.status(Response.Status.CREATED)
        .entity(initiativeCodeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllInitiatives() {
    List<InitiativeDto> initiativesDto = initiativeService.getAllInitiatives();
    GenericEntity<List<InitiativeDto>> entities =
        new GenericEntity<List<InitiativeDto>>(initiativesDto) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/availableAmount")
  public Response getInitiativeAvailableAmount() {
    InitiativeAvailableAmountDto initiativeAvailableAmountDto =
        initiativeService.getAvailableAmount();
    return Response.status(Response.Status.OK)
        .entity(initiativeAvailableAmountDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{code}")
  public Response getInitiative(@PathParam("code") String code) {
    InitiativeDto initiativeDto = initiativeService.getInitiative(code);
    return Response.status(Response.Status.OK)
        .entity(initiativeDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{code}")
  public Response allocateAmountToInitiative(
      @PathParam("code") String code,
      InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDTO) {
    initiativeService.addAllocatedAmountToInitiative(code, initiativeAddAllocatedAmountDTO);
    return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
  }
}
