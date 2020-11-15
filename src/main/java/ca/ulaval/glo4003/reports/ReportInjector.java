package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.reports.api.ReportResource;
import ca.ulaval.glo4003.reports.api.ReportResourceImplementation;
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
import ca.ulaval.glo4003.reports.services.ReportService;

public class ReportInjector {

  private ReportRepository reportRepository = new InMemoryReportRepository();

  public ReportService createReportService() {
    ReportScopeBuilder scopeBuilder = new ReportScopeBuilder();
    ReportMetricBuilder metricBuilder = new InMemoryReportMetricBuilder();
    ReportDimensionBuilder dimensionBuilder = new InMemoryReportDimensionBuilder();
    ReportQueryBuilder reportQueryBuilder =
        new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);

    ReportDimensionDataAssembler reportDimensionDataAssembler = new ReportDimensionDataAssembler();
    ReportMetricDataAssembler reportMetricDataAssembler = new ReportMetricDataAssembler();
    ReportPeriodDataAssembler reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);
    ReportPeriodAssembler reportPeriodAssembler =
        new ReportPeriodAssembler(reportPeriodDataAssembler);

    return new ReportService(
        reportRepository, reportQueryBuilder, reportPeriodAssembler, new ReportEventFactory());
  }

  public ReportResource createReportResource() {
    return new ReportResourceImplementation(createReportService());
  }
}
