package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import java.util.List;
import java.util.stream.Collectors;

// TODO #339 : Test this (copy tests from old builder
public class ReportAggregateFunctionFactoryInMemory {
  public List<ReportAggregateFunctionInMemory> createMany(
      List<ReportAggregateFunctionType> aggregateFunctionTypes) {
    return aggregateFunctionTypes.stream().map(this::createOne).collect(Collectors.toList());
  }

  private ReportAggregateFunctionInMemory createOne(
      ReportAggregateFunctionType aggregateFunctionType) {
    switch (aggregateFunctionType) {
      case AVERAGE:
        return new AverageAggregateFunctionInMemory();
      case MAXIMUM:
        return new MaximumAggregateFunctionInMemory();
      default:
      case MINIMUM:
        return new MinimumAggregateFunctionInMemory();
    }
  }
}
