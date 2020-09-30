package ca.ulaval.glo4003.domain.offense.helpers;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;

import ca.ulaval.glo4003.domain.offense.OffenseValidation;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.time.TimeOfDay;

public class OffenseValidationBuilder {
  private ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private TimeOfDay timeOfDay = createTimeOfDay();

  private OffenseValidationBuilder() {}

  public static OffenseValidationBuilder anOffenseValidation() {
    return new OffenseValidationBuilder();
  }

  public OffenseValidationBuilder withParkingStickerCode(String parkingStickerCode) {
    this.parkingStickerCode = new ParkingStickerCode(parkingStickerCode);
    return this;
  }

  public OffenseValidation build() {
    OffenseValidation offenseValidation =
        new OffenseValidation(parkingStickerCode, parkingAreaCode, timeOfDay);
    return offenseValidation;
  }
}
