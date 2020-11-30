package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reports/parkingAreas")
public class ReportParkingAreaResource {

  private final ReportParkingAreaService reportParkingAreaService;

  public ReportParkingAreaResource(ReportParkingAreaService reportParkingAreaService) {
    this.reportParkingAreaService = reportParkingAreaService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getParkingAreas(
      @DefaultValue("monthly") @QueryParam("reportType") String reportType,
      @DefaultValue("null") @QueryParam("month") String month) {
    List<ReportPeriodDto> reportPeriodsDto =
        reportParkingAreaService.getAllParkingAreaReports(reportType, month);

    return buildResponse(reportPeriodsDto);
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
