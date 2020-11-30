package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import ca.ulaval.glo4003.reports.api.ReportParkingAreaResource;
import ca.ulaval.glo4003.reports.api.ReportParkingAreaResourceImplementation;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.assemblers.*;
import ca.ulaval.glo4003.reports.domain.*;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo4003.reports.infrastructure.ReportQueryBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportRepositoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportSummaryBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ReportDimensionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ReportMetricBuilderInMemory;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;

public class ReportInjector {

  private final ReportRepository reportRepository = new ReportRepositoryInMemory();

  public ReportProfitService createReportProfitService() {
    return new ReportProfitService(
        reportRepository, createReportQueryBuilder(), createReportPeriodAssembler());
  }

  public ReportEventService createReportEventService() {
    return new ReportEventService(reportRepository, new ReportEventFactory());
  }

  public ReportProfitResource createReportProfitResource() {
    return new ReportProfitResource(createReportProfitService());
  }

  public ReportParkingAreaResource createReportParkingAreaResource(
      ParkingAreaService parkingAreaService) {
    return new ReportParkingAreaResourceImplementation(
        createReportParkingAreaService(parkingAreaService));
  }

  public ReportParkingAreaService createReportParkingAreaService(
      ParkingAreaService parkingAreaService) {
    ReportParkingAreaQueryFactory reportParkingAreaQueryFactory =
        new ReportParkingAreaQueryFactory(createReportQueryBuilder());
    ReportAggregateFunctionBuilderInMemory reportAggregateFunctionBuilder =
        new ReportAggregateFunctionBuilderInMemory();
    ReportSummaryBuilder reportSummaryBuilder =
        new ReportSummaryBuilderInMemory(reportAggregateFunctionBuilder);

    return new ReportParkingAreaService(
        parkingAreaService,
        reportRepository,
        createReportPeriodAssembler(),
        reportParkingAreaQueryFactory,
        reportSummaryBuilder);
  }

  private ReportPeriodAssembler createReportPeriodAssembler() {
    ReportDimensionDataAssembler reportDimensionDataAssembler = new ReportDimensionDataAssembler();
    ReportMetricDataAssembler reportMetricDataAssembler = new ReportMetricDataAssembler();
    ReportPeriodDataAssembler reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);
    return new ReportPeriodAssembler(reportPeriodDataAssembler);
  }

  private ReportQueryBuilder createReportQueryBuilder() {
    ReportMetricBuilder metricBuilder = new ReportMetricBuilderInMemory();
    ReportDimensionBuilder dimensionBuilder = new ReportDimensionBuilderInMemory();
    ReportScopeBuilder scopeBuilder = new ReportScopeBuilder();

    return new ReportQueryBuilderInMemory(scopeBuilder, metricBuilder, dimensionBuilder);
  }
}
