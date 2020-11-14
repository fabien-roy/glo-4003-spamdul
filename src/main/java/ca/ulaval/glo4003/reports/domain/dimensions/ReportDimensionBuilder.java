package ca.ulaval.glo4003.reports.domain.dimensions;

import java.util.List;

public interface ReportDimensionBuilder {

  ReportDimensionBuilder someDimensions();

  ReportDimensionBuilder withTypes(List<ReportDimensionType> dimensionTypes);

  List<ReportDimension> buildMany();
}
