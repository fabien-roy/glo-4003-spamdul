package ca.ulaval.glo4003.initiatives.api;

import ca.ulaval.glo4003.initiatives.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeAddAllocatedAmountDto;
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
  Response getAllInitiatives();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/availableAmount")
  Response getInitiativeAvailableAmount();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{initiativeCode}")
  Response getInitiative(@PathParam("initiativeCode") String initiativeCode);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{initiativeCode}")
  Response allocateAmountToInitiative(
      @PathParam("initiativeCode") String initiativeCode,
      InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDTO);
}
