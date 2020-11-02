package ca.ulaval.glo4003.reports.domain.dimensions;

import java.util.List;

// TODO : Will this builder really be for many dimensions or only a single one?
public interface ReportDimensionBuilder {

  ReportDimensionBuilder someDimensions();

  ReportDimensionBuilder withTypes(List<ReportDimensionType> dimensionTypes);

  List<ReportDimension> buildMany();
}
