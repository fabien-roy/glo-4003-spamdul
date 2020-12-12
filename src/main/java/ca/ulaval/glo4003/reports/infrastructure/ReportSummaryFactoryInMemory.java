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

  // TODO #317 : Test this
  @Override
  public List<ReportPeriod> create(
      List<ReportAggregateFunctionType> aggregateFunctionTypes,
      List<ReportPeriod> periods,
      List<ReportMetricType> metrics) {
    List<ReportAggregateFunctionInMemory> aggregateFunctions =
        aggregateFunctionFactory.createMany(aggregateFunctionTypes);

    return aggregateFunctions.stream()
        .flatMap(function -> function.aggregate(periods, metrics).stream())
        .collect(Collectors.toList());
  }
}
