package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO #339 : Delete this
public class ReportAggregateFunctionBuilderInMemory {
  private List<ReportAggregateFunctionType> aggregateFunctionTypes = new ArrayList<>();

  public ReportAggregateFunctionBuilderInMemory someAggregateFunctions() {
    return new ReportAggregateFunctionBuilderInMemory();
  }

  public ReportAggregateFunctionBuilderInMemory withTypes(
      List<ReportAggregateFunctionType> aggregateFunctionTypes) {
    this.aggregateFunctionTypes = aggregateFunctionTypes;
    return this;
  }

  public List<ReportAggregateFunctionInMemory> buildMany() {
    return aggregateFunctionTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportAggregateFunctionInMemory buildOne(
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
