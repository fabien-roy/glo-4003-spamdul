package ca.ulaval.glo4003.api.offense.helpers;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;

public class OffenseValidationDtoBuilder {
  private String parkingStickerCode = createParkingStickerCode().toString();
  private String parkingAreaCode = createParkingAreaCode().toString();
  private String timeOfDay = createTimeOfDay().toString();

  private OffenseValidationDtoBuilder() {}

  public static OffenseValidationDtoBuilder anOffenseValidationDto() {
    return new OffenseValidationDtoBuilder();
  }
}
