package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

public class ReportEvent {

  public final ReportEventType type;
  public final CustomDateTime dateTime;
  public Money profits; // TODO : Use ReportEvent.profits
  public int gateEntries; // TODO : Use ReportEvent.gateEntries
  public ConsumptionType consumptionType; // TODO : Use ReportEvent.consumptionType
  public ParkingAreaCode parkingAreaCode; // TODO : Use ReportEvent.parkingAreaCode

  public ReportEvent(ReportEventType type, CustomDateTime dateTime) {
    this.type = type;
    this.dateTime = dateTime;
  }

  public CustomDateTime getDateTime() {
    return dateTime;
  }

  public ReportEventType getType() {
    return type;
  }
}
