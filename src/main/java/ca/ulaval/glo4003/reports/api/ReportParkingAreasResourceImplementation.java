package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ReportParkingAreasResourceImplementation implements ReportParkingAreasResource {

  private ReportParkingAreasService reportParkingAreasService;

  public ReportParkingAreasResourceImplementation(
      ReportParkingAreasService reportParkingAreasService) {
    this.reportParkingAreasService = reportParkingAreasService;
  }

  @Override
  public Response getParkingAreas(String reportType, String month) {

    List<ReportPeriodDto> reportPeriodsDto =
        reportParkingAreasService.getReports(reportType, month);

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
