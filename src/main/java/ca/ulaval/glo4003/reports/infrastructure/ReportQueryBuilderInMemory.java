package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportEventTypeFilterInMemory;
import ca.ulaval.glo4003.reports.infrastructure.filters.ReportFilterInMemory;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.ArrayList;
import java.util.List;

public class ReportQueryBuilderInMemory implements ReportQueryBuilder<ReportQueryInMemory> {

  private final ReportScopeBuilder scopeBuilder;
  private final ReportMetricBuilder metricBuilder;
  private final ReportDimensionBuilder dimensionBuilder;

  private TimePeriod period;
  private ReportScopeType scopeType;
  private List<ReportDimensionType> dimensionTypes;
  private List<ReportMetricType> metricTypes;
  private final List<ReportFilterInMemory> filters = new ArrayList<>();

  public ReportQueryBuilderInMemory(
      ReportScopeBuilder scopeBuilder,
      ReportMetricBuilder metricBuilder,
      ReportDimensionBuilder dimensionBuilder) {
    this.scopeBuilder = scopeBuilder;
    this.metricBuilder = metricBuilder;
    this.dimensionBuilder = dimensionBuilder;
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> aReportQuery() {
    return new ReportQueryBuilderInMemory(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> withPeriod(TimePeriod period) {
    this.period = period;
    return this;
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> withScope(ReportScopeType scopeType) {
    this.scopeType = scopeType;
    return this;
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> withMetrics(List<ReportMetricType> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> withDimensions(
      List<ReportDimensionType> dimensionTypes) {
    this.dimensionTypes = dimensionTypes;
    return this;
  }

  @Override
  public ReportQueryBuilder<ReportQueryInMemory> withReportEventType(
      ReportEventType reportEventType) {
    ReportFilterInMemory typeFilter = new ReportEventTypeFilterInMemory(reportEventType);
    filters.add(typeFilter);
    return this;
  }

  @Override
  public ReportQueryInMemory build() {
    ReportScope scope = scopeBuilder.aReportScope().withPeriod(period).withType(scopeType).build();
    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();
    List<ReportDimension> dimensions =
        dimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();
    return new ReportQueryInMemory(scope, metrics, dimensions, filters);
  }
}
