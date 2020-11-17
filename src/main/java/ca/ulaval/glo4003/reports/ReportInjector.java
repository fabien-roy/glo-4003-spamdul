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
import ca.ulaval.glo4003.reports.infrastructure.ReportQueryBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.ReportRepositoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.dimensions.ReportDimensionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.metrics.ReportMetricBuilderInMemory;
import ca.ulaval.glo4003.reports.services.ReportService;

public class ReportInjector {

  private ReportRepository reportRepository = new ReportRepositoryInMemory();

  public ReportService createReportService() {
    ReportScopeBuilder scopeBuilder = new ReportScopeBuilder();
    ReportMetricBuilder metricBuilder = new ReportMetricBuilderInMemory();
    ReportDimensionBuilder dimensionBuilder = new ReportDimensionBuilderInMemory();
    ReportQueryBuilder reportQueryBuilder =
        new ReportQueryBuilderInMemory(scopeBuilder, metricBuilder, dimensionBuilder);

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
