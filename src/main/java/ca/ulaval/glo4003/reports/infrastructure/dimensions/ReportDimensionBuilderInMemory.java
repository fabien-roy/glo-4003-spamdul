package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportDimensionBuilderInMemory implements ReportDimensionBuilder {

  private List<ReportDimensionType> dimensionTypes = new ArrayList<>();

  public ReportDimensionBuilderInMemory someDimensions() {
    return new ReportDimensionBuilderInMemory();
  }

  public ReportDimensionBuilderInMemory withTypes(List<ReportDimensionType> dimensionTypes) {
    this.dimensionTypes = dimensionTypes;
    return this;
  }

  public List<ReportDimension> buildMany() {
    return dimensionTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportDimension buildOne(ReportDimensionType dimensionType) {
    switch (dimensionType) {
      default:
      case CONSUMPTION_TYPE:
        return new ConsumptionTypeDimensionInMemory();
      case PARKING_AREA_CODE:
        return null; // TODO #263
    }
  }
}
