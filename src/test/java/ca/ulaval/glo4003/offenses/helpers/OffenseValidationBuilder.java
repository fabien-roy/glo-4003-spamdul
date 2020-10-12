package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.times.helpers.TimeOfDayMother.createTimeOfDay;

import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.times.domain.TimeOfDay;

public class OffenseValidationBuilder {
  private ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private TimeOfDay timeOfDay = createTimeOfDay();

  private OffenseValidationBuilder() {}

  public static OffenseValidationBuilder anOffenseValidation() {
    return new OffenseValidationBuilder();
  }

  public OffenseValidation build() {
    OffenseValidation offenseValidation =
        new OffenseValidation(parkingStickerCode, parkingAreaCode, timeOfDay);
    return offenseValidation;
  }
}
