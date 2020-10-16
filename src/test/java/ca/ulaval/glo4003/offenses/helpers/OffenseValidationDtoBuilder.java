package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.times.helpers.TimeOfDayMother.createTimeOfDay;

import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;

public class OffenseValidationDtoBuilder {
  private String parkingStickerCode = createParkingStickerCode().toString();
  private String parkingAreaCode = createParkingAreaCode().toString();
  private String timeOfDay = createTimeOfDay().toString();

  private OffenseValidationDtoBuilder() {}

  public static OffenseValidationDtoBuilder anOffenseValidationDto() {
    return new OffenseValidationDtoBuilder();
  }

  public OffenseValidationDto build() {
    OffenseValidationDto offenseValidationDto = new OffenseValidationDto();
    offenseValidationDto.parkingStickerCode = parkingStickerCode;
    offenseValidationDto.parkingArea = parkingAreaCode;
    offenseValidationDto.timeOfDay = timeOfDay;
    return offenseValidationDto;
  }
}
