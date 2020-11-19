package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ReportParkingAreaResourceImplementation implements ReportParkingAreaResource {

  private final ReportParkingAreaService reportParkingAreaService;

  public ReportParkingAreaResourceImplementation(
      ReportParkingAreaService reportParkingAreaService) {
    this.reportParkingAreaService = reportParkingAreaService;
  }

  @Override
  public Response getParkingAreas(String reportType, String month) {

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
