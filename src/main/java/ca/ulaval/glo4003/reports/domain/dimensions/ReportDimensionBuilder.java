package ca.ulaval.glo4003.reports.domain.dimensions;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import java.util.List;

public interface ReportDimensionBuilder {

  ReportDimensionBuilder someDimensions();

  ReportDimensionBuilder withTypes(List<ReportDimensionType> dimensionTypes);

  ReportDimensionBuilder withParkingAreaCodes(List<ParkingAreaCode> parkingAreaCodes);

  List<ReportDimension> buildMany();
}
