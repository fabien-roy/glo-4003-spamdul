package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.Map;

public class ParkingArea {
  private final ParkingAreaCode code;
  private final Map<ParkingPeriods, Money> feePerPeriod;

  public ParkingArea(ParkingAreaCode code, Map<ParkingPeriods, Money> feePerPeriod) {
    this.code = code;
    this.feePerPeriod = feePerPeriod;
  }

  public ParkingAreaCode getCode() {
    return code;
  }

  public Money getFeeForPeriod(ParkingPeriods period) {
    return feePerPeriod.get(period);
  }
}
