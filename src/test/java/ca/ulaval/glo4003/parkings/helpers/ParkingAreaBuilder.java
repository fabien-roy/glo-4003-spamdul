package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createFeePerPeriod;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import java.util.Map;

public class ParkingAreaBuilder {
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private Map<ParkingPeriods, Money> feePerPeriod = createFeePerPeriod();

  private ParkingAreaBuilder() {}

  public static ParkingAreaBuilder aParkingArea() {
    return new ParkingAreaBuilder();
  }

  public ParkingAreaBuilder withFeePerPeriod(Map<ParkingPeriods, Money> feePerPeriod) {
    this.feePerPeriod = feePerPeriod;
    return this;
  }

  public ParkingArea build() {
    return new ParkingArea(parkingAreaCode, feePerPeriod);
  }
}
