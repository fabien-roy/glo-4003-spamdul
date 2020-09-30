package ca.ulaval.glo4003.offenses.helpers;

import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;

import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;

public class OffenseValidationDtoBuilder {
  private String parkingStickerCode = createParkingStickerCode().toString();
  private String parkingAreaCode = createParkingAreaCode().toString();
  private String timeOfDay = createTimeOfDay().toString();

  private OffenseValidationDtoBuilder() {}

  public static OffenseValidationDtoBuilder anOffenseValidationDto() {
    return new OffenseValidationDtoBuilder();
  }

  public OffenseValidationDtoBuilder withParkingStickerCode(String parkingStickerCode) {
    this.parkingStickerCode = parkingStickerCode;
    return this;
  }

  public OffenseValidationDtoBuilder withParkingAreaCode(String parkingAreaCode) {
    this.parkingAreaCode = parkingAreaCode;
    return this;
  }

  public OffenseValidationDtoBuilder withTimeOfDay(String timeOfDay) {
    this.timeOfDay = timeOfDay;
    return this;
  }

  public OffenseValidationDto build() {
    OffenseValidationDto offenseValidationDto = new OffenseValidationDto();
    offenseValidationDto.parkingStickerCode = parkingStickerCode;
    offenseValidationDto.parkingArea = parkingAreaCode;
    offenseValidationDto.timeOfDay = timeOfDay;
    return offenseValidationDto;
  }
}
