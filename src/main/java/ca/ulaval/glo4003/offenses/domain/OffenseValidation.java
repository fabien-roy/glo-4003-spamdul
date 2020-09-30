package ca.ulaval.glo4003.offenses.domain;

import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.time.TimeOfDay;

public class OffenseValidation {
  private final ParkingStickerCode parkingStickerCode;
  private final ParkingAreaCode parkingAreaCode;
  private final TimeOfDay timeOfDay;

  public OffenseValidation(
      ParkingStickerCode parkingStickerCode, ParkingAreaCode parkingAreaCode, TimeOfDay timeOfDay) {
    this.parkingStickerCode = parkingStickerCode;
    this.parkingAreaCode = parkingAreaCode;
    this.timeOfDay = timeOfDay;
  }

  public ParkingStickerCode getParkingStickerCode() {
    return parkingStickerCode;
  }

  public ParkingAreaCode getParkingAreaCode() {
    return parkingAreaCode;
  }

  public TimeOfDay getTimeOfDay() {
    return timeOfDay;
  }
}
