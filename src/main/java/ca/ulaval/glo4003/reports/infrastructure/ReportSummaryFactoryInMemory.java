package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportSummaryFactory;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionFactoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionInMemory;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSummaryFactoryInMemory implements ReportSummaryFactory {
  private final ReportAggregateFunctionFactoryInMemory aggregateFunctionFactory;

  public ReportSummaryFactoryInMemory(
      ReportAggregateFunctionFactoryInMemory aggregateFunctionFactory) {
    this.aggregateFunctionFactory = aggregateFunctionFactory;
  }

  // TODO #339 : Test this
  @Override
  public List<ReportPeriod> create(
      List<ReportAggregateFunctionType> aggregateFunctionTypes,
      List<ReportPeriod> periods,
      ReportMetricType metric) {
    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(aggregateFunctionTypes);

    return aggregateFunctions.stream()
        .map(function -> function.aggregate(periods, metric))
        .collect(Collectors.toList());
  }
}
