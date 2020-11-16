package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.List;

public class InMemoryParkingAreaDimension extends ReportDimension<ParkingAreaCode> {

  private final List<ParkingAreaCode> parkingAreaCodes;

  public InMemoryParkingAreaDimension(List<ParkingAreaCode> parkingAreaCodes) {
    this.parkingAreaCodes = parkingAreaCodes;
  }

  @Override
  protected ReportDimensionType getType() {
    return ReportDimensionType.PARKING_AREA;
  }

  @Override
  public List<ParkingAreaCode> getValues() {
    return parkingAreaCodes;
  }

  protected boolean filter(ReportEvent event, ParkingAreaCode value) {
    return event.getParkingAreaCode().equals(value);
  }
}
