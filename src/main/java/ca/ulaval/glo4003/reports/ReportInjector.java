package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.reports.api.ReportParkingAreaResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.infrastructure.ReportRepositoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportSummaryFactoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionFactoryInMemory;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;
import ca.ulaval.glo4003.reports.services.assemblers.*;

public class ReportInjector {

  private final ReportRepository reportRepository = new ReportRepositoryInMemory();

  public ReportProfitService createReportProfitService() {
    return new ReportProfitService(reportRepository);
  }

  public ReportEventService createReportEventService() {
    return new ReportEventService(reportRepository);
  }

  public ReportProfitResource createReportProfitResource() {
    return new ReportProfitResource(createReportProfitService());
  }

  public ReportParkingAreaResource createReportParkingAreaResource(
      ParkingAreaService parkingAreaService) {
    return new ReportParkingAreaResource(createReportParkingAreaService(parkingAreaService));
  }

  public ReportParkingAreaService createReportParkingAreaService(
      ParkingAreaService parkingAreaService) {
    ReportSummaryFactory reportSummaryFactory =
        new ReportSummaryFactoryInMemory(new ReportAggregateFunctionFactoryInMemory());

    return new ReportParkingAreaService(parkingAreaService, reportRepository, reportSummaryFactory);
  }
}
