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
import ca.ulaval.glo4003.reports.infrastructure.filters.InMemoryReportEventTypeFilter;
import ca.ulaval.glo4003.reports.infrastructure.filters.InMemoryReportFilter;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.ArrayList;
import java.util.List;

public class InMemoryReportQueryBuilder implements ReportQueryBuilder<InMemoryReportQuery> {

  private final ReportScopeBuilder scopeBuilder;
  private final ReportMetricBuilder metricBuilder;
  private final ReportDimensionBuilder dimensionBuilder;

  private TimePeriod period;
  private ReportScopeType scopeType;
  private List<ReportDimensionType> dimensionTypes;
  private List<ReportMetricType> metricTypes;
  private List<InMemoryReportFilter> filters = new ArrayList<>();

  public InMemoryReportQueryBuilder(
      ReportScopeBuilder scopeBuilder,
      ReportMetricBuilder metricBuilder,
      ReportDimensionBuilder dimensionBuilder) {
    this.scopeBuilder = scopeBuilder;
    this.metricBuilder = metricBuilder;
    this.dimensionBuilder = dimensionBuilder;
  }

  @Override
  public ReportQueryBuilder<InMemoryReportQuery> aReportQuery() {
    return new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  // TODO #264 : Test
  @Override
  public ReportQueryBuilder<InMemoryReportQuery> withPeriod(TimePeriod period) {
    this.period = period;
    return this;
  }

  // TODO #264 : Test
  @Override
  public ReportQueryBuilder<InMemoryReportQuery> withScope(ReportScopeType scopeType) {
    this.scopeType = scopeType;
    return this;
  }

  // TODO #264 : Test
  @Override
  public ReportQueryBuilder<InMemoryReportQuery> withMetrics(List<ReportMetricType> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  // TODO #264 : Test
  @Override
  public ReportQueryBuilder<InMemoryReportQuery> withDimensions(
      List<ReportDimensionType> dimensionTypes) {
    this.dimensionTypes = dimensionTypes;
    return this;
  }

  // TODO #264 : Test
  @Override
  public ReportQueryBuilder<InMemoryReportQuery> withReportEventType(
      ReportEventType reportEventType) {
    InMemoryReportFilter typeFilter = new InMemoryReportEventTypeFilter(reportEventType);
    filters.add(typeFilter);
    return this;
  }

  @Override
  public InMemoryReportQuery build() {
    ReportScope scope = scopeBuilder.withPeriod(period).withType(scopeType).build();
    List<ReportMetric> metrics = metricBuilder.withTypes(metricTypes).buildMany();
    List<ReportDimension> dimensions = dimensionBuilder.withTypes(dimensionTypes).buildMany();
    return new InMemoryReportQuery(scope, metrics, dimensions, filters);
  }
}
