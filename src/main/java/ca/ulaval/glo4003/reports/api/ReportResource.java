package ca.ulaval.glo4003.reports.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reports")
public interface ReportResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/profits/parkingStickers")
  Response getParkingStickerProfits(@DefaultValue("2020") @QueryParam("year") int year);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/profits/accessPasses")
  Response getAccessPassProfits(
      @DefaultValue("2020") @QueryParam("year") int year,
      @DefaultValue("false") @QueryParam("byConsumptionType") String isByConsumptionType);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/profits/offenses")
  Response getOffenseProfits(@DefaultValue("2020") @QueryParam("year") int year);
}
