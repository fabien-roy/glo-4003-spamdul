package ca.ulaval.glo4003.initiative.api;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/initiatives")
public interface InitiativeResource {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response addInitiative(AddInitiativeDto addInitiativeDto);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getInitiatives();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/availableAmount")
  Response getInitiativeAvailableAmount();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{initiativeCode}")
  Response allocateAmountToInitiative(
      @PathParam("initiativeCode") String initiativeCode,
      InitiativeAddAllocatedAmountDTO initiativeAddAllocatedAmountDTO);
}
