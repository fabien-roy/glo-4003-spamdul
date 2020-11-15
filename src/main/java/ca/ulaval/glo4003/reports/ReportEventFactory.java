package ca.ulaval.glo4003.reports;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

// TODO : Test ReportEventFactory
public class ReportEventFactory {

  public ReportEvent create(ReportEventType type, Money profits) {
    return new ReportEvent(type, CustomDateTime.now(), profits, null);
  }

  public ReportEvent create(ReportEventType type, Money profits, ConsumptionType consumptionType) {
    return new ReportEvent(type, CustomDateTime.now(), profits, consumptionType);
  }
}
