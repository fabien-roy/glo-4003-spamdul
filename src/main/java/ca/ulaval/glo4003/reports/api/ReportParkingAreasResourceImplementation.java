package ca.ulaval.glo4003.reports.api;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import java.util.List;
import javax.ws.rs.core.Response;

public class ReportParkingAreasResourceImplementation implements ReportParkingAreasResource {

  private ReportParkingAreasService reportParkingAreasService;

  public ReportParkingAreasResourceImplementation(
      ReportParkingAreasService reportParkingAreasService) {
    this.reportParkingAreasService = reportParkingAreasService;
  }

  @Override
  public Response getParkingAreas(int year, String reportType, String month) {

    List<ReportPeriodDto> reportPeriodsDto =
        reportParkingAreasService.getReports(year, reportType, month);

    return ReportProfitResourceImplementation.buildResponse(reportPeriodsDto);
  }
}
