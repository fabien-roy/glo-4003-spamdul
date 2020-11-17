package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.reports.api.ReportParkingAreasResource;
import ca.ulaval.glo4003.reports.api.ReportParkingAreasResourceImplementation;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResourceImplementation;
import ca.ulaval.glo4003.reports.assemblers.ReportDimensionDataAssembler;
import ca.ulaval.glo4003.reports.assemblers.ReportMetricDataAssembler;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodAssembler;
import ca.ulaval.glo4003.reports.assemblers.ReportPeriodDataAssembler;
import ca.ulaval.glo4003.reports.domain.ReportEventFactory;
import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.ReportRepository;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo4003.reports.infrastructure.InMemoryReportQueryBuilder;
import ca.ulaval.glo4003.reports.infrastructure.InMemoryReportRepository;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.InMemoryReportDimensionBuilder;
import ca.ulaval.glo4003.reports.infrastructure.metrics.InMemoryReportMetricBuilder;
import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;

public class ReportInjector {

  private ReportRepository reportRepository = new InMemoryReportRepository();
  private ReportMetricBuilder metricBuilder = new InMemoryReportMetricBuilder();
  private ReportDimensionBuilder dimensionBuilder = new InMemoryReportDimensionBuilder();
  private ReportScopeBuilder scopeBuilder = new ReportScopeBuilder();
  private ReportQueryBuilder reportQueryBuilder =
      new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);

  public ReportProfitService createReportProfitService() {

    return new ReportProfitService(
        reportRepository,
        reportQueryBuilder,
        createReportPeriodAssembler(),
        new ReportEventFactory());
  }

  public ReportProfitResource createReportProfitResource() {
    return new ReportProfitResourceImplementation(createReportProfitService());
  }

  public ReportParkingAreasResource createReportParkingAreaResource() {
    return new ReportParkingAreasResourceImplementation(createReportParkingAreaService());
  }

  public ReportParkingAreasService createReportParkingAreaService() {

    return new ReportParkingAreasService(
        reportRepository, reportQueryBuilder, createReportPeriodAssembler());
  }

  private ReportPeriodAssembler createReportPeriodAssembler() {
    ReportDimensionDataAssembler reportDimensionDataAssembler = new ReportDimensionDataAssembler();
    ReportMetricDataAssembler reportMetricDataAssembler = new ReportMetricDataAssembler();
    ReportPeriodDataAssembler reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);
    return new ReportPeriodAssembler(reportPeriodDataAssembler);
  }
}
