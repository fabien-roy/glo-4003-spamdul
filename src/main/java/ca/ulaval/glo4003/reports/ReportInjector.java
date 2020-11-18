package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.reports.api.ReportParkingAreasResource;
import ca.ulaval.glo4003.reports.api.ReportParkingAreasResourceImplementation;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResourceImplementation;
import ca.ulaval.glo4003.reports.assemblers.*;
import ca.ulaval.glo4003.reports.domain.ReportEventFactory;
import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.ReportRepository;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo4003.reports.infrastructure.ReportQueryBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportRepositoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ReportDimensionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ReportMetricBuilderInMemory;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;

public class ReportInjector {

  private ReportRepository reportRepository = new ReportRepositoryInMemory();
  private ReportMetricBuilder metricBuilder = new ReportMetricBuilderInMemory();
  private ReportDimensionBuilder dimensionBuilder = new ReportDimensionBuilderInMemory();
  private ReportScopeBuilder scopeBuilder = new ReportScopeBuilder();
  private ReportQueryBuilder reportQueryBuilder =
      new ReportQueryBuilderInMemory(scopeBuilder, metricBuilder, dimensionBuilder);

  public ReportProfitService createReportProfitService() {

    return new ReportProfitService(
        reportRepository, reportQueryBuilder, createReportPeriodAssembler());
  }

  public ReportEventService createReportEventService() {
    return new ReportEventService(reportRepository, new ReportEventFactory());
  }

  public ReportProfitResource createReportProfitResource() {
    return new ReportProfitResourceImplementation(createReportProfitService());
  }

  public ReportParkingAreasResource createReportParkingAreaResource() {
    return new ReportParkingAreasResourceImplementation(createReportParkingAreaService());
  }

  public ReportParkingAreasService createReportParkingAreaService() {
    ReportQueryFactory reportQueryFactory = new ReportQueryFactory(reportQueryBuilder);
    return new ReportParkingAreasService(
        reportRepository, createReportPeriodAssembler(), reportQueryFactory);
  }

  private ReportPeriodAssembler createReportPeriodAssembler() {
    ReportDimensionDataAssembler reportDimensionDataAssembler = new ReportDimensionDataAssembler();
    ReportMetricDataAssembler reportMetricDataAssembler = new ReportMetricDataAssembler();
    ReportPeriodDataAssembler reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);
    return new ReportPeriodAssembler(reportPeriodDataAssembler);
  }
}
