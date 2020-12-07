package ca.ulaval.glo4003.offenses.services.converters;

import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.times.domain.TimeOfDay;
import ca.ulaval.glo4003.times.services.converters.TimeOfDayConverter;

public class OffenseValidationConverter {
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final TimeOfDayConverter timeOfDayConverter;

  public OffenseValidationConverter() {
    this(
        new ParkingStickerCodeAssembler(),
        new ParkingAreaCodeAssembler(),
        new TimeOfDayConverter());
  }

  public OffenseValidationConverter(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayConverter timeOfDayConverter) {
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.timeOfDayConverter = timeOfDayConverter;
  }

  public OffenseValidation convert(OffenseValidationDto offenseValidationDto) {
    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea);
    TimeOfDay timeOfDay = timeOfDayConverter.convert(offenseValidationDto.timeOfDay);

    return new OffenseValidation(parkingStickerCode, parkingAreaCode, timeOfDay);
  }
}
