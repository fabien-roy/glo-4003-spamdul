package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.reports.helpers.ReportEventMother.createReportEventType;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createDateTime;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEvent;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

public class ReportEventBuilder {
  private ReportEventType type = createReportEventType();
  private CustomDateTime dateTime = createDateTime();
  private Money profits = null;
  private ConsumptionType consumptionType = null;
  private ParkingAreaCode parkingAreaCode = null;

  public static ReportEventBuilder aReportEvent() {
    return new ReportEventBuilder();
  }

  public ReportEventBuilder withType(ReportEventType type) {
    this.type = type;
    return this;
  }

  public ReportEventBuilder withDateTime(CustomDateTime dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  public ReportEventBuilder withProfits(Money profits) {
    this.profits = profits;
    return this;
  }

  public ReportEventBuilder withConsumptionType(ConsumptionType consumptionType) {
    this.consumptionType = consumptionType;
    return this;
  }

  public ReportEventBuilder withParkingAreaCode(ParkingAreaCode parkingAreaCode) {
    this.parkingAreaCode = parkingAreaCode;
    return this;
  }

  public ReportEvent build() {
    if (parkingAreaCode == null) {
      return consumptionType == null
          ? new ReportEvent(type, dateTime, profits)
          : new ReportEvent(type, dateTime, profits, consumptionType);
    }

    return new ReportEvent(type, dateTime, parkingAreaCode);
  }
}
