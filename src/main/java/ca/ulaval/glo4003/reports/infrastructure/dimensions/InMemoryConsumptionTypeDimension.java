package ca.ulaval.glo4003.reports.infrastructure.dimensions;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import java.util.Arrays;
import java.util.List;

public class InMemoryConsumptionTypeDimension extends ReportDimension<ConsumptionType> {

  @Override
  protected ReportDimensionType getType() {
    return ReportDimensionType.CONSUMPTION_TYPE;
  }

  @Override
  public List<ConsumptionType> getValues() {
    return Arrays.asList(ConsumptionType.values());
  }

  protected boolean filter(ReportEvent event, ConsumptionType value) {
    return event.getConsumptionType().equals(value);
  }
}
