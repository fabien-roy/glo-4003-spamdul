package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

public class ReportEvent {

  public final ReportEventType type;
  public final CustomDateTime dateTime;
  public Money profits;
  public int gateEntries; // TODO #249 : Use ReportEvent.gateEntries
  public ConsumptionType consumptionType;
  public ParkingAreaCode parkingAreaCode; // TODO #263 : Use ReportEvent.parkingAreaCode

  public ReportEvent(ReportEventType type, CustomDateTime dateTime, Money profits) {
    this.type = type;
    this.dateTime = dateTime;
    this.profits = profits;
  }

  public CustomDateTime getDateTime() {
    return dateTime;
  }

  public ReportEventType getType() {
    return type;
  }

  public Money getProfits() {
    return profits;
  }

  public ConsumptionType getConsumptionType() {
    return consumptionType;
  }
}
