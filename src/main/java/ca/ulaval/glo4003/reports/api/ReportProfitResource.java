package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.services.ReportProfitService;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDto;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reports/profits")
public class ReportProfitResource {
  private final ReportProfitService reportProfitService;

  public ReportProfitResource(ReportProfitService reportProfitService) {
    this.reportProfitService = reportProfitService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/parkingStickers")
  public Response getParkingStickerProfits(@DefaultValue("-1") @QueryParam("year") int year) {
    List<ReportPeriodDto> periodDtos =
        reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, year);

    return buildResponse(periodDtos);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/accessPasses")
  public Response getAccessPassProfits(
      @DefaultValue("-1") @QueryParam("year") int year,
      @DefaultValue("false") @QueryParam("byConsumptionType") String isByConsumptionType) {
    List<ReportPeriodDto> periodDtos =
        reportProfitService.getAllProfits(
            ReportEventType.BILL_PAID_FOR_ACCESS_PASS,
            year,
            Boolean.parseBoolean(isByConsumptionType));

    return buildResponse(periodDtos);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/offenses")
  public Response getOffenseProfits(@DefaultValue("-1") @QueryParam("year") int year) {
    List<ReportPeriodDto> periodDtos =
        reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_OFFENSE, year);

    return buildResponse(periodDtos);
  }

  private Response buildResponse(List<ReportPeriodDto> periodDtos) {
    GenericEntity<List<ReportPeriodDto>> entities =
        new GenericEntity<List<ReportPeriodDto>>(periodDtos) {};

    return Response.status(Response.Status.OK)
        .entity(entities)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
