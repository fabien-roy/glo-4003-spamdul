package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportSummaryBuilder;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionInMemory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO #238 : Test ReportSummaryBuilderInMemory
public class ReportSummaryBuilderInMemory implements ReportSummaryBuilder {

  private final ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilder;

  private List<ReportPeriod> periods = new ArrayList<>();
  private List<ReportAggregateFunctionType> aggregateFunctionTypes = new ArrayList<>();
  private ReportMetricType metric;

  public ReportSummaryBuilderInMemory(
      ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilder) {
    this.aggregateFunctionBuilder = aggregateFunctionBuilder;
  }

  @Override
  public ReportSummaryBuilder aReportSummary() {
    return new ReportSummaryBuilderInMemory(aggregateFunctionBuilder);
  }

  @Override
  public ReportSummaryBuilder withPeriods(List<ReportPeriod> periods) {
    this.periods = periods;
    return this;
  }

  @Override
  public ReportSummaryBuilder withAggregateFunctions(
      List<ReportAggregateFunctionType> aggregateFunctionTypes) {
    this.aggregateFunctionTypes = aggregateFunctionTypes;
    return this;
  }

  @Override
  public ReportSummaryBuilder withMetric(ReportMetricType metric) {
    this.metric = metric;
    return this;
  }

  @Override
  public List<ReportPeriod> build() {
    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionBuilder
            .someAggregateFunctions()
            .withTypes(aggregateFunctionTypes)
            .buildMany();

    return aggregateFunctions.stream()
        .map(function -> function.aggregate(periods, metric))
        .collect(Collectors.toList());
  }
}
