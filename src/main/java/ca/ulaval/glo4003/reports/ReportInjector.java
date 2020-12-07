package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.reports.api.ReportParkingAreaResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeFactory;
import ca.ulaval.glo4003.reports.infrastructure.ReportQueryFactoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportRepositoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportSummaryBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionBuilderInMemory;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;
import ca.ulaval.glo4003.reports.services.assemblers.*;

public class ReportInjector {

  private final ReportRepository reportRepository = new ReportRepositoryInMemory();

  public ReportProfitService createReportProfitService() {
    return new ReportProfitService(
        reportRepository, createReportQueryFactory(), createReportPeriodAssembler());
  }

  public ReportEventService createReportEventService() {
    return new ReportEventService(reportRepository, new ReportEventFactory());
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
    ReportAggregateFunctionBuilderInMemory reportAggregateFunctionBuilder =
        new ReportAggregateFunctionBuilderInMemory();
    ReportSummaryBuilder reportSummaryBuilder =
        new ReportSummaryBuilderInMemory(reportAggregateFunctionBuilder);

    return new ReportParkingAreaService(
        parkingAreaService,
        reportRepository,
        createReportPeriodAssembler(),
        createReportQueryFactory(),
        reportSummaryBuilder);
  }

  private ReportPeriodAssembler createReportPeriodAssembler() {
    ReportDimensionDataAssembler reportDimensionDataAssembler = new ReportDimensionDataAssembler();
    ReportMetricDataAssembler reportMetricDataAssembler = new ReportMetricDataAssembler();
    ReportPeriodDataAssembler reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);
    return new ReportPeriodAssembler(reportPeriodDataAssembler);
  }

  private ReportQueryFactory createReportQueryFactory() {
    return new ReportQueryFactoryInMemory(new ReportScopeFactory());
  }
}
