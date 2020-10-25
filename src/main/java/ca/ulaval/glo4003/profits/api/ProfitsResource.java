package ca.ulaval.glo4003.profits.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profits")
public interface ProfitsResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/parkingStickers")
  Response getParkingStickerProfits(@DefaultValue("2020") @QueryParam("year") int year);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/accessPasses")
  Response getAccessPassProfits(
      @DefaultValue("2020") @QueryParam("year") int year,
      @DefaultValue("false") @QueryParam("byConsumptionType") String isByConsumptionType);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/offenses")
  Response getOffenseProfits(@DefaultValue("2020") @QueryParam("year") int year);
}
